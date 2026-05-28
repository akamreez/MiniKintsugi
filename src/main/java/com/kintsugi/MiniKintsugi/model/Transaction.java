package com.kintsugi.MiniKintsugi.model;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


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

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    @NotBlank
    private String state;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    private Integer riskScore;
    public Transaction(Long id, String customerName, BigDecimal amount, String state, TransactionStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.state = state;
        this.status = status;
    }

}
