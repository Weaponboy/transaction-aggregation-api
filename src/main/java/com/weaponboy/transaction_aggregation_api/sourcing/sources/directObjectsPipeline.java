package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transactionImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class directObjectsPipeline {

    public List<transaction> getSampleTransactions() {
        List<transaction> transactions = new ArrayList<>();

        transactions.add(new transactionImpl("001", 120.50, new Date(), "Amazon", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("002", 75.20, new Date(), "Walmart", "Bank of America", "Nothing happened here"));
        transactions.add(new transactionImpl("003", 200.00, new Date(), "Target", "Wells Fargo", "Nothing happened here"));
        transactions.add(new transactionImpl("004", 15.75, new Date(), "Starbucks", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("005", 320.40, new Date(), "Best Buy", "Citi", "Nothing happened here"));
        transactions.add(new transactionImpl("006", 60.00, new Date(), "Uber", "Capital One", "Nothing happened here"));
        transactions.add(new transactionImpl("007", 89.99, new Date(), "Apple", "Chase", "Nothing happened here"));
        transactions.add(new transactionImpl("008", 45.10, new Date(), "Netflix", "Discover", "Nothing happened here"));
        transactions.add(new transactionImpl("009", 150.25, new Date(), "Costco", "Wells Fargo", "Nothing happened here"));
        transactions.add(new transactionImpl("010", 22.30, new Date(), "McDonald's", "Bank of America", "Nothing happened here"));

        return transactions;
    }

}
