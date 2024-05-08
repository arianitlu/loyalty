package com.raiffeisen.loyalty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoyaltyController {

    @GetMapping("/loyalty-points")
    private List<LoyaltyPoints> getLoyaltyPonts(){
        List<LoyaltyPoints> loyaltyPointsList = new ArrayList<>();
        loyaltyPointsList.add(new LoyaltyPoints(12L, 5800, PointStatus.PENDING));
        loyaltyPointsList.add(new LoyaltyPoints(13L, 5800, PointStatus.AVAILABLE));

        return loyaltyPointsList;
    }
}
