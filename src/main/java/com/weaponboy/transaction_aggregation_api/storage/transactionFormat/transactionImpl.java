package com.weaponboy.transaction_aggregation_api.storage.transactionFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class transactionImpl implements transaction {

    private Long id;
    private final String account;
    private final BigDecimal transactionAmount;
    private final LocalDate date;
    private final String merchant;
    private final String bank;
    private final String description;

    public transactionImpl(String account, BigDecimal transactionAmount, LocalDate date, String merchant, String bank, String description) {
        this.account = account;
        this.transactionAmount = transactionAmount;
        this.date = date;
        this.merchant = merchant;
        this.bank = bank;
        this.description = description;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String account() {
        return account;
    }

    @Override
    public BigDecimal transactionAmount() {
        return transactionAmount;
    }

    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public String merchant() {
        return merchant;
    }

    @Override
    public String bank() {
        return bank;
    }

    @Override
    public String description() {
        return description;
    }
}