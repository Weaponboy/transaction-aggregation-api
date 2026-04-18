package com.weaponboy.transaction_aggregation_api.storeForUse.format;

import java.util.*;

public class transactionImpl implements transaction {

    private final String account;
    private final double transactionAmount;
    private final Date date;
    private final String merchant;
    private final String bank;
    private final String description;

    public transactionImpl(String account, double transactionAmount, Date date, String merchant, String bank, String description) {
        this.account = account;
        this.transactionAmount = transactionAmount;
        this.date = date;
        this.merchant = merchant;
        this.bank = bank;
        this.description = description;
    }

    @Override
    public String account() {
        return account;
    }

    @Override
    public double transactionAmount() {
        return transactionAmount;
    }

    @Override
    public Date date() {
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