package com.weaponboy.transaction_aggregation_api.accessAPI.returnData;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class AggregatedTransactionsDto {

    private String customerId;
    private long totalTransactions;
    private BigDecimal totalAmount;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;

    private Map<String, BigDecimal> amountByCategory;
    private Map<String, Long> countByCategory;
}

