package com.weaponboy.transaction_aggregation_api.API;

import com.weaponboy.transaction_aggregation_api.storage.sorting.TransactionService;
import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class transactionReturnAPI {

    private final TransactionService service;

    public transactionReturnAPI(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionEntity> getAll() {
        return service.returnAllTransactions();
    }

    @GetMapping("/paged")
    public Page<TransactionEntity> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction dir = direction.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return service.getTransactionsPaginated(pageable);
    }

    @GetMapping("/account/{account}")
    public List<TransactionEntity> getByAccountName(@PathVariable String account) {
        return service.findByAccount(account);
    }

    @GetMapping("/merchant/{merchant}")
    public List<TransactionEntity> getByMerchant(@PathVariable String merchant) {
        return service.findByMerchant(merchant);
    }

    @GetMapping("/bank/{bank}")
    public List<TransactionEntity> getByBank(@PathVariable String bank) {
        return service.findByBank(bank);
    }

    @GetMapping("/amount/greater-than")
    public List<TransactionEntity> getByAmountGreaterThan(@RequestParam BigDecimal amount) {
        return service.findByAmountGreaterThan(amount);
    }

    @GetMapping("/date/range")
    public List<TransactionEntity> getByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate must be before or equal to endDate");
        }
        return service.findByDateBetween(startDate, endDate);
    }

    @GetMapping("/search")
    public List<TransactionEntity> search(@RequestParam String keyword) {
        return service.searchByDescription(keyword);
    }

    @GetMapping("/filter")
    public List<TransactionEntity> filterTransactions(
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String bank,
            @RequestParam(required = false) String merchant,
            @RequestParam(required = false) BigDecimal minAmount) {

        if (account == null && bank == null && merchant == null && minAmount == null) {
            throw new IllegalArgumentException("At least one filter parameter must be inputted");
        }

        if (account != null) {
            return service.findByAccount(account);
        }
        if (bank != null) {
            return service.findByBank(bank);
        }
        if (merchant != null) {
            return service.findByMerchant(merchant);
        }
        if (minAmount != null) {
            return service.findByAmountGreaterThan(minAmount);
        }

        return service.returnAllTransactions();
    }

}