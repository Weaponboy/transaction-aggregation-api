package com.weaponboy.transaction_aggregation_api;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.aggregate;
import com.weaponboy.transaction_aggregation_api.sourcing.returnRaw;

public class program {

    aggregate aggregate;
    returnRaw returnRawData = new returnRaw();

    public void run(){
        aggregate = new aggregate(returnRawData);
    }

}
