package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.transaction;
import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.transactionImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class directObjectsPipeline {

    public List<transaction> getSampleTransactions() {
        List<transaction> transactions = new ArrayList<>();

        transactions.add(new transactionImpl("001", new BigDecimal("120.50"), LocalDate.now(), "Amazon", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("002", new BigDecimal("75.20"), LocalDate.now(), "Walmart", "Bank of America", "Nothing happened here"));
        transactions.add(new transactionImpl("003", new BigDecimal("200.00"), LocalDate.now(), "Target", "Wells Fargo", "Nothing happened here"));
        transactions.add(new transactionImpl("004", new BigDecimal("15.75"), LocalDate.now(), "Starbucks", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("005", new BigDecimal("320.40"), LocalDate.now(), "Best Buy", "Citi", "Nothing happened here"));
        transactions.add(new transactionImpl("006", new BigDecimal("60.60"), LocalDate.now(), "Uber", "Capital One", "Nothing happened here"));
        transactions.add(new transactionImpl("007", new BigDecimal("89.99"), LocalDate.now(), "Apple", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("008", new BigDecimal("45.10"), LocalDate.now(), "Netflix", "Discover", "Nothing happened here"));
        transactions.add(new transactionImpl("009", new BigDecimal("150.25"), LocalDate.now(), "Costco", "Wells Fargo", "Nothing happened here"));
        transactions.add(new transactionImpl("010", new BigDecimal("22.30"), LocalDate.now(), "McDonald's", "Bank of America", "Nothing happened here"));

        return transactions;
    }

}
