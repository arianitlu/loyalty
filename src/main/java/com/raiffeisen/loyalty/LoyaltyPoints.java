package com.raiffeisen.loyalty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoyaltyPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loylatyPointsId;
    private int points;
    private PointStatus status;

    public LoyaltyPoints() {
    }

    public LoyaltyPoints(long loylatyPointsId, int points, PointStatus status) {
        this.loylatyPointsId = loylatyPointsId;
        this.points = points;
        this.status = status;
    }

    public long getLoylatyPointsId() {
        return loylatyPointsId;
    }

    public void setLoylatyPointsId(long loylatyPointsId) {
        this.loylatyPointsId = loylatyPointsId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PointStatus getStatus() {
        return status;
    }

    public void setStatus(PointStatus status) {
        this.status = status;
    }
}
