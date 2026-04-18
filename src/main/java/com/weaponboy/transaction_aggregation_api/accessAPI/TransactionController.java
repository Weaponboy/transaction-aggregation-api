package com.weaponboy.transaction_aggregation_api.accessAPI;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.TransactionEntity;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;
import com.weaponboy.transaction_aggregation_api.accessAPI.returnData.AggregatedTransactionsDto;
import com.weaponboy.transaction_aggregation_api.accessAPI.returnData.returnSortedTransactions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for retrieving and managing aggregated transaction data.
 * This is the "returning data" part of the application.
 */
@RestController
@RequestMapping("/api/v1/customers/{customerId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final returnSortedTransactions aggregationService = new returnSortedTransactions();

    /**
     * GET - Return rich aggregated information (the main extensive API).
     * Example URL: /api/v1/customers/CUST123/transactions/aggregated?period=LAST_30_DAYS
     */
    @GetMapping("/aggregated")
    public ResponseEntity<AggregatedTransactionsDto> getAggregatedTransactions(
            @PathVariable String customerId,
            @RequestParam(defaultValue = "LAST_30_DAYS") String period) {

        AggregatedTransactionsDto result = aggregationService.returnAggregatedData(customerId, period);
        return ResponseEntity.ok(result);
    }

    /**
     * GET - Return raw/paginated list of categorized transactions (optional but makes API more extensive).
     * Supports filtering and pagination.
     */
    @GetMapping
    public ResponseEntity<List<transaction>> getTransactions (){
        List<transaction> transactions = aggregationService.returnRawData();
        return ResponseEntity.ok(transactions);
    }

}