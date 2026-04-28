package com.weaponboy.transaction_aggregation_api.storage.sorting;

import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Exposes transaction repository methods to
 * */
@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void saveTransactions(List<TransactionEntity> transactions) {
        repository.saveAll(transactions);
    }

    public List<TransactionEntity> returnAllTransactions() {
        return repository.findAll();
    }

    public Page<TransactionEntity> getTransactionsPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<TransactionEntity> findByAccount(String account) {
        return repository.findByAccount(account);
    }

    public List<TransactionEntity> findByAmountGreaterThan(BigDecimal amount) {
        return repository.findByAmountGreaterThan(amount);
    }

    public List<TransactionEntity> findByDateBetween(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }

    public List<TransactionEntity> findByMerchant(String merchant) {
        return repository.findByMerchant(merchant);
    }

    public List<TransactionEntity> findByBank(String bank) {
        return repository.findByBank(bank);
    }

    public List<TransactionEntity> searchByDescription(String keyword) {
        return repository.findByDescriptionContainingIgnoreCase(keyword);
    }

    public Optional<TransactionEntity> findById(String customerName) {
        return repository.findById(customerName);
    }
}