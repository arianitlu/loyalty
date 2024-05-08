package com.raiffeisen.loyalty;


public class LoyaltyPoints {

    private long loylatyPointsId;
    private int points;
    private PointStatus status;

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
