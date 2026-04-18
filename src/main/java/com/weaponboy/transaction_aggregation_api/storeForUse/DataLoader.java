package com.weaponboy.transaction_aggregation_api.storeForUse;

import com.weaponboy.transaction_aggregation_api.storeForUse.format.TransactionEntity;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.sourcing.inputRawData.TransactionProvider;
import com.weaponboy.transaction_aggregation_api.dbManagement.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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
        System.out.println("Data loaded: " + entities.size());
    }
}