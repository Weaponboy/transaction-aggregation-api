package com.weaponboy.transaction_aggregation_api.sourcing.inputRawData;

import com.weaponboy.transaction_aggregation_api.storeForUse.format.transaction;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.directObjectsPipeline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProvider {

    private ArrayList<transaction> rawTransactionFiles = new ArrayList<>();

    //Data sources
    private directObjectsPipeline data1 = new directObjectsPipeline();

    public void aggregateData(){
        rawTransactionFiles.addAll(data1.getSampleTransactions());
    }

    public List<transaction> getTransactions() {
        return rawTransactionFiles;
    }
}