package com.weaponboy.transaction_aggregation_api.storage;

import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.TransactionEntity;
import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.transaction;
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
        provider.aggregateData();
        List<transaction> raw = provider.getTransactions();

        List<TransactionEntity> entities = raw.stream()
                .map(t -> {
                    TransactionEntity e = new TransactionEntity();
                    e.setId(t.id());
                    e.setAccount(t.account());
                    e.setAmount(t.transactionAmount());
                    e.setDate(t.date());
                    e.setMerchant(t.merchant());
                    e.setBank(t.bank());
                    e.setDescription(t.description());
                    return e;
                })
                .toList();

        service.saveTransactions(entities);
        System.out.println("Number of transactions loaded: " + entities.size());
    }
}