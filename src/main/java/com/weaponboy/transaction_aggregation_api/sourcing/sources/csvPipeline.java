package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Spring Boot production-ready CSV processor.
 *
 * Reads CSV files from src/main/resources
 * Converts rows into standardized Transaction objects
 */
@Component
public class csvPipeline {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("M/d/yyyy");

    /**
     * Load CSV from resources folder.
     *
     * Example:
     * process("mockData/data1/transaction_data.csv");
     */
    public List<Transaction> process(String resourcePath) throws Exception {

        ClassPathResource resource =
                new ClassPathResource(resourcePath);

        try (InputStream inputStream = resource.getInputStream()) {
            return process(inputStream);
        }
    }

    /**
     * Process any input stream.
     */
    public List<Transaction> process(InputStream inputStream) throws Exception {

        List<Transaction> transactions = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .setIgnoreSurroundingSpaces(true)
                .setQuote('"')
                .setDelimiter(',')
                .build();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8)
                );

                CSVParser parser = format.parse(reader)
        ) {

            for (CSVRecord row : parser) {

                try {
                    Transaction tx = mapRow(row);
                    transactions.add(tx);

                } catch (Exception ex) {
                    System.out.println(
                            "Skipping row "
                                    + row.getRecordNumber()
                                    + " -> "
                                    + ex.getMessage()
                    );
                }
            }
        }

        return transactions;
    }

    /**
     * Maps CSV row into standard transaction object.
     *
     * Required headers:
     * id,date,amount,description,account
     */
    private Transaction mapRow(CSVRecord row) throws Exception {

        String userId = row.get("UserId");

        String transactionId = row.get("TransactionId");

        String rawTime = row.get("TransactionTime");

        int quantity = Integer.parseInt(row.get("NumberOfItemsPurchased"));

        double cost = Double.parseDouble(row.get("CostPerItem"));

        double total = quantity * cost;

        Date date = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss z yyyy"
        ).parse(rawTime);

        String itemDescription = row.get("ItemDescription");

        String country = row.get("Country");

        return new Transaction(
                userId,
                total,
                date,
                itemDescription,
                country
        );
    }

    /**
     * Standardized transaction model.
     */
    public static class Transaction {

        private final String customerName;
        private final double amount;
        private final Date date;
        private final String merchant;
        private final String bank;

        public Transaction(
                String customerName,
                double amount,
                Date date,
                String merchant,
                String bank
        ) {
            this.customerName = customerName;
            this.amount = amount;
            this.date = date;
            this.merchant = merchant;
            this.bank = bank;
        }

        public String customerName() {
            return customerName;
        }

        public double transactionAmount() {
            return amount;
        }

        public Date date() {
            return date;
        }

        public String merchant() {
            return merchant;
        }

        public String bank() {
            return bank;
        }
    }
}