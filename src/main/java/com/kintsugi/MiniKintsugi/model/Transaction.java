package com.kintsugi.MiniKintsugi.model;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {


    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String customerName;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String state;

    @NotNull
    private TransactionStatus status;

    public Transaction(Long id, String customerName, BigDecimal amount, String state, TransactionStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.state = state;
        this.status = status;
    }

}
