package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transactionImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class hardcodeObjectsTesting {

    public List<transaction> getSampleTransactions() {
        List<transaction> transactions = new ArrayList<>();

        transactions.add(new transactionImpl("Alice Johnson", 120.50, new Date(), "Amazon", "Chase"));
        transactions.add(new transactionImpl("Bob Smith", 75.20, new Date(), "Walmart", "Bank of America"));
        transactions.add(new transactionImpl("Charlie Brown", 200.00, new Date(), "Target", "Wells Fargo"));
        transactions.add(new transactionImpl("Diana Prince", 15.75, new Date(), "Starbucks", "Chase"));
        transactions.add(new transactionImpl("Ethan Hunt", 320.40, new Date(), "Best Buy", "Citi"));
        transactions.add(new transactionImpl("Fiona Gallagher", 60.00, new Date(), "Uber", "Capital One"));
        transactions.add(new transactionImpl("George Martin", 89.99, new Date(), "Apple", "Chase"));
        transactions.add(new transactionImpl("Hannah Baker", 45.10, new Date(), "Netflix", "Discover"));
        transactions.add(new transactionImpl("Ian Malcolm", 150.25, new Date(), "Costco", "Wells Fargo"));
        transactions.add(new transactionImpl("Jane Doe", 22.30, new Date(), "McDonald's", "Bank of America"));

        return transactions;
    }

}
