package com.weaponboy.transaction_aggregation_api.categorizingAndSorting;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.TransactionEntity;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.transactionServices.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

import static com.weaponboy.transaction_aggregation_api.categorizingAndSorting.aggregate.getTransactions;

@Component
public class ScheduledDataLoader {

    private final TransactionService service;

    public ScheduledDataLoader(TransactionService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 10000)
    public void loadData() {
        List<transaction> raw = getTransactions();

        List<TransactionEntity> entities = raw.stream()
                .map(t -> {
                    TransactionEntity e = new TransactionEntity();
                    e.setId(t.customerName());
                    e.setAmount(t.transactionAmount());
                    e.setDescription(t.bank());
                    return e;
                })
                .toList();

        service.saveTransactions(entities);

        System.out.println("Data loaded: " + entities.size());
    }
}