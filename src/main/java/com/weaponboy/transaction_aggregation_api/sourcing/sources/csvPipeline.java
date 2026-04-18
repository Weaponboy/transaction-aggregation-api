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
            String dateStr = record.get("Date");
            String description = record.get("Description");
            String amountStr = record.get("Amount");
            String account = record.get("Account");

            LocalDate localDate = LocalDate.parse(dateStr, DATE_FORMATTER);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            double amount = parseAmount(amountStr);

            return new transactionImpl(
                    account,
                    amount,
                    date,
                    description,
                    bankOrMerchant(),
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
        return "N/A";
    }
}