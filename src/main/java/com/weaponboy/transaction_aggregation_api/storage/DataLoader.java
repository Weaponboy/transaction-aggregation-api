package com.weaponboy.transaction_aggregation_api.storage;

import com.weaponboy.transaction_aggregation_api.sourcing.inputData.TransactionProvider;
import com.weaponboy.transaction_aggregation_api.storage.sorting.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Makes use of a Spring Boot component to store aggregated data
 * */

@Component
public class DataLoader implements CommandLineRunner {

    private final TransactionService service;
    private final TransactionProvider provider;

    public DataLoader(TransactionService service, TransactionProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    @Override
    public void run(String... args) throws Exception {
        service.saveTransactions(provider.getTransactions());
    }
}