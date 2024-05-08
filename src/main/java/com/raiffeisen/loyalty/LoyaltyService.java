package com.raiffeisen.loyalty;

import com.raiffeisen.loyalty.models.LoyaltyPoints;
import com.raiffeisen.loyalty.models.PointStatus;
import com.raiffeisen.loyalty.models.Summary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoyaltyService {

    LoyaltyRepository loyaltyRepository;

    public LoyaltyService(LoyaltyRepository loyaltyRepository) {
        this.loyaltyRepository = loyaltyRepository;
    }

    public List<LoyaltyPoints> getLoyaltyPoints(){
        // added this method just for testing via api
        updateLoyaltyPointsStatus();
        return loyaltyRepository.findAll();
    }

    // Better option to use Scheduler to run this method once an end of week
    @Scheduled(cron = "0 59 23 * * SUN")
    public void updateLoyaltyPointsStatus(){
        // sample date
        LocalDateTime now = LocalDateTime.of(2022, 6, 19, 0, 0);

        LocalDateTime fiveWeeksAgo = now.minusWeeks(5);
        loyaltyRepository.expireOldLoyaltyPoints(PointStatus.EXPIRED, fiveWeeksAgo);

        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime endOfWeek = now.with(DayOfWeek.SUNDAY).with(LocalTime.MAX);

        List<LoyaltyPoints> potentialPoints = loyaltyRepository.findAllPointsInWeek(startOfWeek, endOfWeek);

        Map<Long, List<LoyaltyPoints>> customerIdToLoyaltyPoints = potentialPoints.stream()
                .collect(Collectors.groupingBy(LoyaltyPoints::getCustomerId));

        customerIdToLoyaltyPoints.forEach((customerId, loyaltyPointsList) -> {
            System.out.println("Customer ID: " + customerId);

            double totalWeekSpending = loyaltyPointsList.stream().mapToDouble(LoyaltyPoints::getTransactionAmount).sum();
            System.out.println("Customer spend: " + totalWeekSpending);

            if (totalWeekSpending >= 500) {

                Set<DayOfWeek> transactionDays = loyaltyPointsList.stream()
                        .map(loyaltyPoint -> loyaltyPoint.getTransactionDate().getDayOfWeek())
                        .collect(Collectors.toSet());

                boolean hasTransactionsEveryDay = transactionDays.size() == 7;
                System.out.println("Transactions on every day of the week: " + hasTransactionsEveryDay);

                if (hasTransactionsEveryDay) {
                    loyaltyPointsList.forEach(lp -> {
                        lp.setStatus(PointStatus.AVAILABLE);
                        loyaltyRepository.save(lp);
                    });
                }
            }

        });
    }

    public Summary getSummary(Long customerId) {
        List<LoyaltyPoints> loyaltyPointsList = loyaltyRepository.findLoyaltyPointsByCustomerId(customerId);

        int availablePoints = loyaltyPointsList.stream()
                .filter(loyaltyPoints -> PointStatus.AVAILABLE.equals(loyaltyPoints.getStatus()))
                .map(LoyaltyPoints::getPoints)
                .reduce(Integer::sum)
                .orElse(0);

        int pendingPoints = loyaltyPointsList.stream()
                .filter(loyaltyPoints -> PointStatus.PENDING.equals(loyaltyPoints.getStatus()))
                .map(LoyaltyPoints::getPoints)
                .reduce(Integer::sum)
                .orElse(0);

        int spentPoints = loyaltyPointsList.stream()
                .filter(loyaltyPoints -> PointStatus.SPENT.equals(loyaltyPoints.getStatus()))
                .map(LoyaltyPoints::getPoints)
                .reduce(Integer::sum)
                .orElse(0);

        return new Summary(customerId, pendingPoints, availablePoints, spentPoints, loyaltyPointsList);

    }
}
