package com.kintsugi.MiniKintsugi.dto;

import com.kintsugi.MiniKintsugi.model.TransactionStatus;
import java.math.BigDecimal;

public class TransactionResponseDTO {

    private Long id;
    private String customerName;
    private BigDecimal amount;
    private String state;
    private TransactionStatus status;

    public TransactionResponseDTO() {

    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    private Integer riskScore;

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}
