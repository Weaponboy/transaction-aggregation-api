package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transactionImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
            double amount      = node.path("amount").asDouble();
            String currency    = node.path("currency").asText();
            String description = node.path("description").asText();
            String category    = node.path("category").asText();
            String dateStr     = node.path("date").asText();
            String status      = node.path("status").asText();

            // Convert String date "2024-01-01" → java.util.Date
            LocalDate localDate = LocalDate.parse(dateStr);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Map to your transactionImpl constructor
            // Adjust the order and parameters to match your actual transactionImpl constructor
            return new transactionImpl(
                    accountId,      // customerName / account
                    amount,
                    date,
                    description,
                    "Default Bank", // bank field
                    type            // type
            );

        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON transaction: " + node, e);
        }
    }
}