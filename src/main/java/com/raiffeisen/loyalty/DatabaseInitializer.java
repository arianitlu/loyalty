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
        loyaltyPointsRepository.save(new LoyaltyPoints(1L, 100, 550, LocalDateTime.of(2022, 6, 13, 10, 30), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(2L, 101, 300, LocalDateTime.of(2022, 6, 14, 12, 30), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(3L, 102, 450, LocalDateTime.of(2022, 6, 15, 15, 45), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(4L, 103, 700, LocalDateTime.of(2022, 6, 16, 16, 00), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(5L, 104, 200, LocalDateTime.of(2022, 6, 17, 11, 15), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(6L, 105, 800, LocalDateTime.of(2022, 6, 18, 14, 30), PointStatus.PENDING));
        loyaltyPointsRepository.save(new LoyaltyPoints(7L, 106, 600, LocalDateTime.of(2022, 6, 19, 9, 45), PointStatus.PENDING));
    }
}
