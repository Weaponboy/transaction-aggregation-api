package com.weaponboy.transaction_aggregation_api.storeForUse.format;

import java.util.Date;

public interface transaction {

    String account();

    double transactionAmount();

    Date date();

    String merchant();

    String bank();

    String description();
}
