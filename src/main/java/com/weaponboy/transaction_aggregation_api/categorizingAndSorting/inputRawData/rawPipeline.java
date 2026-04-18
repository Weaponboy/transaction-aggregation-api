package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.inputRawData;

import com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format.transaction;

import java.io.File;
import java.util.ArrayList;

public interface rawPipeline {

    ArrayList<transaction> rawTransactions();
}
