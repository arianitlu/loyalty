package com.raiffeisen.loyalty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoyaltyController {

    LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping("/loyalty-points")
    public List<LoyaltyPoints> getLoyaltyPonts(){
        return loyaltyService.getLoyaltyPoints();
    }

    @GetMapping("/customers/{customerId}/summary")
    public Summary getSummary(@PathVariable Long customerId) {
        return loyaltyService.getSummary(customerId);
    }
}
