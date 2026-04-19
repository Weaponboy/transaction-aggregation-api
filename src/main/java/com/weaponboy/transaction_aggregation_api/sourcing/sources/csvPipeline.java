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
public class csvPipeline {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<transaction> getTransactions() {
        ClassPathResource resource = new ClassPathResource("mockData/bank_records.csv");

        if (!resource.exists()) {
            throw new RuntimeException("CSV data file not found: mockData/bank_records.csv");
        }

        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .setIgnoreEmptyLines(true)
                     .setTrim(true)
                     .build())) {

            List<transaction> transactions = StreamSupport.stream(csvParser.spliterator(), false)
                    .map(this::parseRecordToTransaction)
                    .collect(Collectors.toList());

            return transactions;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: mockData/bank_records.csv", e);
        }
    }

    private transaction parseRecordToTransaction(CSVRecord record) {
        try {
            String accountId       = record.get("account_id");
            String transactionDate = record.get("transaction_date");
            String amountStr       = record.get("amount");
            String description     = record.get("description");
            String merchantName    = record.get("merchant_name");
            String transactionType = record.get("transaction_type");

            // Parse date
            LocalDate localDate = LocalDate.parse(transactionDate.trim(), DATE_FORMATTER);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            double amount = parseAmount(amountStr);

            return new transactionImpl(
                    accountId.trim(),
                    amount,
                    date,
                    description != null ? description.trim() : "",
                    "N/A",
                    transactionType != null ? transactionType.trim() : ""
            );

        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV record at line " + record.getRecordNumber()
                    + ": " + record, e);
        }
    }

    private double parseAmount(String amountStr) {
        if (amountStr == null || amountStr.trim().isEmpty()) {
            return 0.0;
        }
        String cleaned = amountStr.trim().replace(",", "");
        return Double.parseDouble(cleaned);
    }
}