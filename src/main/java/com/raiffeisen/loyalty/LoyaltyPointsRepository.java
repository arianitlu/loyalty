package com.raiffeisen.loyalty;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Long> {

    @Query("SELECT lp FROM LoyaltyPoints lp WHERE lp.transactionDate BETWEEN :startOfWeek AND :endOfWeek")
    List<LoyaltyPoints> findAllPointsInWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

    @Modifying
    @Transactional
    @Query("UPDATE LoyaltyPoints lp SET lp.status = :newStatus WHERE lp.transactionDate < :threshold")
    int expireOldLoyaltyPoints(PointStatus newStatus, LocalDateTime threshold);

    List<LoyaltyPoints> findLoyaltyPointsByCustomerId(Long customerId);
}
