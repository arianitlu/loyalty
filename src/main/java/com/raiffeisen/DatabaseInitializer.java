package com.raiffeisen;

import com.raiffeisen.loyalty.models.LoyaltyPoints;
import com.raiffeisen.loyalty.LoyaltyRepository;
import com.raiffeisen.loyalty.models.PointStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final LoyaltyRepository loyaltyRepository;

    public DatabaseInitializer(LoyaltyRepository loyaltyRepository) {
        this.loyaltyRepository = loyaltyRepository;
    }

    @Override
    public void run(String... args) {
        loyaltyRepository.save(new LoyaltyPoints(1L, 200, 100, 550, LocalDateTime.of(2022, 6, 13, 10, 30), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(2L, 200, 101, 300, LocalDateTime.of(2022, 6, 14, 12, 30), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(3L, 200, 102, 450, LocalDateTime.of(2022, 6, 15, 15, 45), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(4L, 200, 103, 700, LocalDateTime.of(2022, 6, 16, 16, 00), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(5L, 200, 104, 200, LocalDateTime.of(2022, 6, 17, 11, 15), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(6L, 200, 105, 800, LocalDateTime.of(2022, 6, 18, 14, 30), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(7L, 200, 106, 600, LocalDateTime.of(2022, 6, 19, 9, 45), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(8L, 201, 107, 600, LocalDateTime.of(2022, 6, 19, 9, 45), PointStatus.PENDING));
        loyaltyRepository.save(new LoyaltyPoints(9L, 202, 108, 600, LocalDateTime.of(2000, 6, 19, 9, 45), PointStatus.PENDING));

    }
}
