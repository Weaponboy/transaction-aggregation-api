package com.weaponboy.transaction_aggregation_api.returnAPI;

import com.weaponboy.transaction_aggregation_api.dbManagement.TransactionService;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.TransactionEntity;
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

    @PostMapping
    public ResponseEntity<String> save(@RequestBody List<TransactionEntity> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return ResponseEntity.badRequest().body("No transactions provided");
        }
        service.saveTransactions(transactions);
        return ResponseEntity.ok("Successfully saved " + transactions.size() + " transactions.");
    }

    @GetMapping
    public List<TransactionEntity> getAll() {
        return service.returnAllTransactions();
    }

    // ==================== Pagination + Sorting (Best for large datasets) ====================

    @GetMapping("/paged")
    public Page<TransactionEntity> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "date") String sortBy,      // default sort by date
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction dir = direction.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return service.getTransactionsPaginated(pageable);
    }

    // ==================== Query by specific fields ====================

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
    public List<TransactionEntity> getByAmountGreaterThan(@RequestParam double amount) {
        return service.findByAmountGreaterThan(amount);
    }

    // ==================== Date Range Query (Fixed for your entity) ====================

    @GetMapping("/date/range")
    public List<TransactionEntity> getByDateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return service.findByDateBetween(startDate, endDate);
    }

    // ==================== Search ====================

    @GetMapping("/search")
    public List<TransactionEntity> search(@RequestParam String keyword) {
        return service.searchByDescription(keyword);
    }

    // ==================== Single Transaction ====================

    @GetMapping("/{customerName}")
    public ResponseEntity<TransactionEntity> getById(@PathVariable String customerName) {
        return service.findById(customerName)  // You'll need to add this in Service
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ==================== Simple Filter (Multiple params) ====================

    @GetMapping("/filter")
    public List<TransactionEntity> filterTransactions(
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String bank,
            @RequestParam(required = false) String merchant,
            @RequestParam(required = false) Double minAmount) {

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