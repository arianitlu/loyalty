package com.raiffeisen.loyalty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final LoyaltyPointsRepository loyaltyPointsRepository;

    public DatabaseInitializer(LoyaltyPointsRepository loyaltyPointsRepository) {
        this.loyaltyPointsRepository = loyaltyPointsRepository;
    }

    @Override
    public void run(String... args) {
        LoyaltyPoints loyaltyPoints1 = new LoyaltyPoints(1L, 100, PointStatus.AVAILABLE);
        LoyaltyPoints loyaltyPoints2 = new LoyaltyPoints(2L, 200, PointStatus.PENDING);
        loyaltyPointsRepository.save(loyaltyPoints1);
        loyaltyPointsRepository.save(loyaltyPoints2);
    }
}
