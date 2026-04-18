package com.weaponboy.transaction_aggregation_api.sourcing.sources;

import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transactionImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class xmlPipeline {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<transaction> getTransactions() {
        ClassPathResource resource = new ClassPathResource("mockData/data1.csv");

        if (!resource.exists()) {
            throw new RuntimeException("CSV data file not found: mockData/data1.csv");
        }

        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .builder()
                     .setHeader()                    // Use the first row as header
                     .setSkipHeaderRecord(true)      // Skip the header row
                     .setIgnoreEmptyLines(true)
                     .setTrim(true)                  // Automatically trim whitespace
                     .build())) {

            List<transaction> transactions = StreamSupport.stream(csvParser.spliterator(), false)
                    .map(this::parseRecordToTransaction)
                    .collect(Collectors.toList());

            System.out.println("Parsed " + transactions.size() + " transactions from CSV"); // ← Add this for debugging
            return transactions;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: mockData/data1.csv", e);
        }
    }

    private transaction parseRecordToTransaction(CSVRecord record) {
        try {
            String dateStr = record.get("Date");
            String description = record.get("Description");   // You were missing this earlier
            String amountStr = record.get("Amount");
            String account = record.get("Account");

            LocalDate localDate = LocalDate.parse(dateStr, DATE_FORMATTER);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            double amount = parseAmount(amountStr);

            return new transactionImpl(
                    account,
                    amount,
                    date,
                    description,      // Added description
                    bankOrMerchant(), // You can improve this later
                    ""
            );

        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV record at line " + record.getRecordNumber() + ": " + record, e);
        }
    }

    private double parseAmount(String amountStr) {
        String cleaned = amountStr.trim().replace("+", "").replace(",", "");
        return Double.parseDouble(cleaned);
    }

    private String bankOrMerchant() {
        return "CHK Bank";
    }
}