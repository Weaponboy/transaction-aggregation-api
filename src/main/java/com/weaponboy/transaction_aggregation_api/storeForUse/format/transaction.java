package com.weaponboy.transaction_aggregation_api.storeForUse.format;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface transaction {

    Long id();

    String account();

    BigDecimal transactionAmount();

    LocalDate date();

    String merchant();

    String bank();

    String description();
}
