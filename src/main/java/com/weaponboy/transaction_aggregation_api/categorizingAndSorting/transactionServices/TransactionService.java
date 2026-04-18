package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.transactionServices;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.TransactionEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    // SAVE
    public void saveTransactions(List<TransactionEntity> transactions) {
        repository.saveAll(transactions);
    }

    // FETCH
    public List<TransactionEntity> getAllTransactions() {
        return repository.findAll();
    }
}