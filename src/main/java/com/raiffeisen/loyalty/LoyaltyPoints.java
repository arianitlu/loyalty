package com.raiffeisen.loyalty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoyaltyPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loylatyPointsId;

    private long transactionId;
    private int transactionAmount;
    private LocalDateTime transactionDate;
    private int points;
    private PointStatus status;

    public LoyaltyPoints() {
    }

    public LoyaltyPoints(long loylatyPointsId, long transactionId, int transactionAmount, LocalDateTime transactionDate, int points,  PointStatus status) {
        this.loylatyPointsId = loylatyPointsId;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
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

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
