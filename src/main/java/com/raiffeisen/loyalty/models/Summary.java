package com.raiffeisen.loyalty.models;

import java.util.List;

public class Summary {

    private long customerId;
    private int pendingPoints;
    private int availablePoints;
    private int spentPoints;

    private List<LoyaltyPoints> loyaltyPointsHistory;

    public Summary(long customerId, int pendingPoints, int availablePoints, int spentPoints, List<LoyaltyPoints> loyaltyPointsHistory) {
        this.customerId = customerId;
        this.pendingPoints = pendingPoints;
        this.availablePoints = availablePoints;
        this.spentPoints = spentPoints;
        this.loyaltyPointsHistory = loyaltyPointsHistory;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getPendingPoins() {
        return pendingPoints;
    }

    public void setPendingPoins(int pendingPoins) {
        this.pendingPoints = pendingPoins;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public List<LoyaltyPoints> getLoyaltyPointsHistory() {
        return loyaltyPointsHistory;
    }

    public void setLoyaltyPointsHistory(List<LoyaltyPoints> loyaltyPointsHistory) {
        this.loyaltyPointsHistory = loyaltyPointsHistory;
    }

    public int getSpentPoints() {
        return spentPoints;
    }

    public void setSpentPoints(int spentPoints) {
        this.spentPoints = spentPoints;
    }
}
