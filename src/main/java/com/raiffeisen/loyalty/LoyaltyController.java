package com.raiffeisen.loyalty;

import com.raiffeisen.loyalty.models.LoyaltyPoints;
import com.raiffeisen.loyalty.models.Summary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoyaltyController {

    LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping("/loyalty-points")
    public List<LoyaltyPoints> getLoyaltyPoints(){
        return loyaltyService.getLoyaltyPoints();
    }

    @GetMapping("/customers/{customerId}/summary")
    public Summary getSummary(@PathVariable Long customerId) {
        return loyaltyService.getSummary(customerId);
    }
}
