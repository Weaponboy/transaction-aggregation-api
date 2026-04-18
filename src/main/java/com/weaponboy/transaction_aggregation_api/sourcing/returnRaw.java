package com.weaponboy.transaction_aggregation_api.sourcing;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.inputRawData.rawPipeline;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.hardcodeObjectsTesting;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.s3Bucket;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.website;
import com.weaponboy.transaction_aggregation_api.sourcing.sources.xmlFile;

import java.io.File;
import java.util.ArrayList;

public class returnRaw implements rawPipeline {

    private ArrayList<transaction> rawTransactionFiles = new ArrayList<>();

    //Data sources
    private hardcodeObjectsTesting data1 = new hardcodeObjectsTesting();

    @Override
    public ArrayList<transaction> rawTransactions() {
        rawTransactionFiles.addAll(data1.getSampleTransactions());
        return rawTransactionFiles;
    }

}
