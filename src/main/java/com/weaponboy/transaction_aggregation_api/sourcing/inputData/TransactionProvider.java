package com.weaponboy.transaction_aggregation_api.sourcing.inputData;

import com.weaponboy.transaction_aggregation_api.sourcing.sources.csvPipeline;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.jsonPipeline;
import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.directObjectsPipeline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Boot service to collect data from pipeline classes to be stored and indexed
 * */
@Service
public class TransactionProvider {

    private ArrayList<transaction> rawTransactionFiles = new ArrayList<>();

    private directObjectsPipeline objectData = new directObjectsPipeline();
    private csvPipeline csvData = new csvPipeline();
    private jsonPipeline jsonData = new jsonPipeline();

    public void aggregateData(){
        rawTransactionFiles.addAll(objectData.getSampleTransactions());
        rawTransactionFiles.addAll(csvData.getTransactions());
        rawTransactionFiles.addAll(jsonData.getTransactions());
    }

    public List<transaction> getTransactions() {
        return rawTransactionFiles;
    }
}