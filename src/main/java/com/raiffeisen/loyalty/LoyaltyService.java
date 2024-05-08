package com.raiffeisen.loyalty;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).with(LocalTime.MIN);;
        LocalDateTime endOfWeek = now.with(DayOfWeek.SUNDAY).with(LocalTime.MAX);;

        List<LoyaltyPoints> potentialPoints = loyaltyPointsRepository.findAllPointsInWeek(startOfWeek, endOfWeek);
        double totalWeekSpending = potentialPoints.stream().mapToDouble(LoyaltyPoints::getTransactionAmount).sum();

        if (totalWeekSpending >= 500) {
            List<Date> transactionDates = loyaltyPointsRepository.findDistinctTransactionDates(startOfWeek, endOfWeek);

            if (transactionDates.size() == 7) {
                potentialPoints.forEach(lp -> {
                    lp.setStatus(PointStatus.AVAILABLE);
                    loyaltyPointsRepository.save(lp);
                });
            }
        }
    }
}
