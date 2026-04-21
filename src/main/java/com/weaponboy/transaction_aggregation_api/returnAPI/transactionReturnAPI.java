package com.weaponboy.transaction_aggregation_api.returnAPI;

import com.weaponboy.transaction_aggregation_api.dbManagement.TransactionService;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.TransactionEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public List<TransactionEntity> getByAccountName(@PathVariable @NotBlank String account) {
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
    public List<TransactionEntity> getByAmountGreaterThan(@RequestParam double amount) {
        return service.findByAmountGreaterThan(amount);
    }

    @GetMapping("/date/range")
    public List<TransactionEntity> getByDateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("startDate must be before or equal to endDate");
        }
        return service.findByDateBetween(startDate, endDate);
    }

    @GetMapping("/searchDescription")
    public List<TransactionEntity> search(@RequestParam @NotBlank String keyword) {
        return service.searchByDescription(keyword);
    }

    @GetMapping("/filter")
    public List<TransactionEntity> filterTransactions(
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String bank,
            @RequestParam(required = false) String merchant,
            @RequestParam(required = false) Double minAmount) {

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