package com.weaponboy.transaction_aggregation_api.categorizingAndSorting;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;
import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.inputRawData.rawPipeline;

import java.util.List;
public class aggregate {

    static rawPipeline rawDataStream;

    public aggregate(rawPipeline rawData){
        rawDataStream = rawData;
    }

    public static List<transaction> getTransactions() {
        return rawDataStream.rawTransactions();
    }


}
