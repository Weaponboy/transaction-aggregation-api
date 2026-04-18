package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.transactionServices;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}