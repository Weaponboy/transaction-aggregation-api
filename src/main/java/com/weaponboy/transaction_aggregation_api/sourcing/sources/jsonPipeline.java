package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.transaction;
import com.weaponboy.transaction_aggregation_api.storage.transactionFormat.transactionImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class jsonPipeline {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public List<transaction> getTransactions() {
        ClassPathResource resource = new ClassPathResource("mockData/bank_records.json");

        if (!resource.exists()) {
            throw new RuntimeException("JSON data file not found: mockData/bank_records.json");
        }

        try {
            JsonNode root = objectMapper.readTree(resource.getInputStream());
            JsonNode transactionsArray = root.path("transactions");

            List<transaction> result = new ArrayList<>();

            for (JsonNode node : transactionsArray) {
                transaction tx = parseJsonToTransaction(node);
                result.add(tx);
            }

            return result;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON file: mockData/bank_records.json", e);
        }
    }

    /**
     * Converts one JSON transaction object into your transactionImpl
     */
    private transaction parseJsonToTransaction(JsonNode node) {
        try {
            String id          = node.path("id").asText();
            String accountId   = node.path("account_id").asText();
            String type        = node.path("type").asText();
            BigDecimal amount  = BigDecimal.valueOf(node.path("amount").asDouble());
            String currency    = node.path("currency").asText();
            String description = node.path("description").asText();
            String category    = node.path("category").asText();
            String dateStr     = node.path("date").asText();
            String status      = node.path("status").asText();

            LocalDate localDate = LocalDate.parse(dateStr);

            return new transactionImpl(
                    accountId,
                    amount,
                    localDate,
                    description,
                    "N/A",
                    type
            );

        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON transaction: " + node, e);
        }
    }
}