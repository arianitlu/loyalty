package com.raiffeisen.loyalty;

import org.springframework.web.bind.annotation.GetMapping;
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
    private List<LoyaltyPoints> getLoyaltyPonts(){
        return loyaltyService.getLoyaltyPoints();
    }
}
