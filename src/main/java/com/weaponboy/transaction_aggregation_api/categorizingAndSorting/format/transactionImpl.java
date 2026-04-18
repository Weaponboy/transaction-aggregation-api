package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format;

import java.util.*;

public class transactionImpl implements transaction {

    private final String customerName;
    private final double transactionAmount;
    private final Date date;
    private final String merchant;
    private final String bank;

    public transactionImpl(String customerName, double transactionAmount, Date date, String merchant, String bank) {
        this.customerName = customerName;
        this.transactionAmount = transactionAmount;
        this.date = date;
        this.merchant = merchant;
        this.bank = bank;
    }

    @Override
    public String customerName() {
        return customerName;
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
}