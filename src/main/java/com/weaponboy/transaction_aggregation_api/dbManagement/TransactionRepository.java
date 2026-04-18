package com.weaponboy.transaction_aggregation_api.dbManagement;

import com.weaponboy.transaction_aggregation_api.storeForUse.format.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String>,
        JpaSpecificationExecutor<TransactionEntity> {

    List<TransactionEntity> findByCustomerName(String customerName);

    List<TransactionEntity> findByAmountGreaterThan(double amount);

    List<TransactionEntity> findByAmountBetween(double minAmount, double maxAmount);

    // Correct date method (field is called "date")
    List<TransactionEntity> findByDateBetween(Date startDate, Date endDate);

    List<TransactionEntity> findByMerchant(String merchant);

    List<TransactionEntity> findByBank(String bank);

    List<TransactionEntity> findByDescriptionContainingIgnoreCase(String keyword);
}