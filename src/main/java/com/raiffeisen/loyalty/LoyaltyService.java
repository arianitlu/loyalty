package com.raiffeisen.loyalty;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyService {

    LoyaltyPointsRepository loyaltyPointsRepository;

    public LoyaltyService(LoyaltyPointsRepository loyaltyPointsRepository) {
        this.loyaltyPointsRepository = loyaltyPointsRepository;
    }

    public List<LoyaltyPoints> getLoyaltyPoints(){
        return loyaltyPointsRepository.findAll();
    }
}
