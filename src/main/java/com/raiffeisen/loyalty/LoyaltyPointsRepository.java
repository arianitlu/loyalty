package com.raiffeisen.loyalty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Long> {

    @Query("SELECT lp FROM LoyaltyPoints lp WHERE lp.transactionDate BETWEEN :startOfWeek AND :endOfWeek")
    List<LoyaltyPoints> findAllPointsInWeek(LocalDateTime startOfWeek, LocalDateTime endOfWeek);
    @Query("SELECT DISTINCT CAST(lp.transactionDate AS date) FROM LoyaltyPoints lp WHERE lp.transactionDate BETWEEN :startOfWeek AND :endOfWeek")
    List<Date> findDistinctTransactionDates(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

}
