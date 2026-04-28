package com.weaponboy.transaction_aggregation_api.storage.sorting;

import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Collection of methods used to sort and query the data in the database for the API
 * */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String>,
        JpaSpecificationExecutor<TransactionEntity> {

    List<TransactionEntity> findByAccount(String account);

    List<TransactionEntity> findByAmountGreaterThan(BigDecimal amount);

    List<TransactionEntity> findByAmountBetween(double minAmount, double maxAmount);

    List<TransactionEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<TransactionEntity> findByMerchant(String merchant);

    List<TransactionEntity> findByBank(String bank);

    List<TransactionEntity> findByDescriptionContainingIgnoreCase(String keyword);
}