package com.raiffeisen.loyalty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final LoyaltyPointsRepository loyaltyPointsRepository;

    public DatabaseInitializer(LoyaltyPointsRepository loyaltyPointsRepository) {
        this.loyaltyPointsRepository = loyaltyPointsRepository;
    }

    @Override
    public void run(String... args) {
        LoyaltyPoints loyaltyPoints1 = new LoyaltyPoints(1L, 100, 4500, LocalDateTime.of(2022, 6, 15, 10, 30), 4500, PointStatus.PENDING);
        LoyaltyPoints loyaltyPoints2 = new LoyaltyPoints(2L, 101,7800, LocalDateTime.of(2022, 6, 15, 10, 30),10900, PointStatus.PENDING);
        loyaltyPointsRepository.save(loyaltyPoints1);
        loyaltyPointsRepository.save(loyaltyPoints2);
    }
}
