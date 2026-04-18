package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format;

import java.util.Date;

public interface transaction {

    String customerName();

    double transactionAmount();

    Date date();

    String merchant();

    String bank();
}
