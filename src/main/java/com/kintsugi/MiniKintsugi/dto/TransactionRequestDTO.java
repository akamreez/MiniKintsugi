package com.kintsugi.MiniKintsugi.dto;
import com.kintsugi.MiniKintsugi.model.TransactionStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransactionRequestDTO {

    @NotBlank
    private String customerName;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String State;

    @NotNull
    private TransactionStatus status;

    public TransactionRequestDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getState() {
        return State;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setState(String state) {
        State = state;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
