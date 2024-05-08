package com.raiffeisen.loyalty;

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

    LoyaltyPointsRepository loyaltyPointsRepository;

    public LoyaltyService(LoyaltyPointsRepository loyaltyPointsRepository) {
        this.loyaltyPointsRepository = loyaltyPointsRepository;
    }

    public List<LoyaltyPoints> getLoyaltyPoints(){
        updateLoyaltyStatus();
        return loyaltyPointsRepository.findAll();
    }

    public void updateLoyaltyStatus(){
        LocalDateTime now = LocalDateTime.of(2022, 6, 19, 0, 0);

        LocalDateTime fiveWeeksAgo = now.minusWeeks(5);
        int updatedCount = loyaltyPointsRepository.expireOldLoyaltyPoints(PointStatus.EXPIRED, fiveWeeksAgo);

        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        LocalDateTime endOfWeek = now.with(DayOfWeek.SUNDAY).with(LocalTime.MAX);

        List<LoyaltyPoints> potentialPoints = loyaltyPointsRepository.findAllPointsInWeek(startOfWeek, endOfWeek);

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
                        loyaltyPointsRepository.save(lp);
                    });
                }
            }

        });
    }
}
