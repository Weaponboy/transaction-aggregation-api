package com.weaponboy.transaction_aggregation_api.categorizingAndSorting.format;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private String id;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private double amount;
    private String description;

    // getters & setters
}