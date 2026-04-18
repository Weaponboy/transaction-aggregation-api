package com.weaponboy.transaction_aggregation_api.accessAPI;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.transactionServices.TransactionService;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.TransactionEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionControllerTest {

    private final TransactionService service;

    public TransactionControllerTest(TransactionService service) {
        this.service = service;
    }

    // POST: Save data
    @PostMapping
    public String save(@RequestBody List<TransactionEntity> transactions) {
        service.saveTransactions(transactions);
        return "Saved!";
    }

    // GET: Retrieve data
    @GetMapping
    public List<TransactionEntity> getAll() {
        return service.getAllTransactions();
    }
}