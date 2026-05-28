package com.kintsugi.MiniKintsugi.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kintsugi.MiniKintsugi.model.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kintsugi.MiniKintsugi.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.kintsugi.MiniKintsugi.model.Transaction;
import com.kintsugi.MiniKintsugi.dto.TransactionRequestDTO;
import com.kintsugi.MiniKintsugi.dto.TransactionResponseDTO;
import org.springframework.web.bind.annotation.*;

import static com.kintsugi.MiniKintsugi.model.TransactionStatus.APPROVED;
import static com.kintsugi.MiniKintsugi.model.TransactionStatus.UNDER_REVIEW;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(
                    TransactionService.class
            );


    public TransactionResponseDTO createTransaction( TransactionRequestDTO dto){
        Transaction transaction = new Transaction();

        transaction.setCustomerName(dto.getCustomerName());
        transaction.setAmount(dto.getAmount());
        transaction.setState(dto.getState());
        transaction.setStatus(dto.getStatus());

        int riskScore = calculateRiskScore(
                dto.getAmount(),
                dto.getState()
        );

        transaction.setRiskScore(riskScore);
        if(riskScore >= 70) {

            transaction.setStatus(
                    TransactionStatus.UNDER_REVIEW
            );

        } else {

            transaction.setStatus(
                    TransactionStatus.APPROVED
            );
        }
        Transaction savedTransaction =
                transactionRepository.save(transaction);

        logger.info(
                "Creating transaction for customer: {}",
                transaction.getCustomerName()
        );

        logger.info(
                "Transaction created with ID: {}",
                savedTransaction.getId()
        );


        TransactionResponseDTO responseDTO =
                new TransactionResponseDTO();


        responseDTO.setId(savedTransaction.getId());

        responseDTO.setCustomerName(
                savedTransaction.getCustomerName());

        responseDTO.setAmount(
                savedTransaction.getAmount());

        responseDTO.setState(
                savedTransaction.getState());

        responseDTO.setStatus(
                savedTransaction.getStatus());

        responseDTO.setRiskScore(
                savedTransaction.getRiskScore()
        );

        logger.info(
                "Transaction created with ID: {}",
                savedTransaction.getId()
        );
        return responseDTO;

    }
    public Transaction approveTransaction(
            Long transactionId
    ) {

        Transaction transaction =
                transactionRepository
                        .findById(transactionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transaction not found"
                                )
                        );

        transaction.setStatus(
                TransactionStatus.APPROVED
        );

        return transactionRepository.save(
                transaction
        );
    }

    public Transaction rejectTransaction(
            Long transactionId
    ) {

        Transaction transaction =
                transactionRepository
                        .findById(transactionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transaction not found"
                                )
                        );

        transaction.setStatus(
                TransactionStatus.REJECTED
        );

        return transactionRepository.save(
                transaction
        );
    }
    private int calculateRiskScore(
            BigDecimal amount,
            String state
    ) {

        int score = 10;

        if(amount.compareTo(
                BigDecimal.valueOf(10000)
        ) > 0) {

            score += 40;
        }

        if(state.equalsIgnoreCase("CA")) {

            score += 20;
        }

        return score;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }


    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
        }


    public boolean deleteTransaction(Long id) {
        if(transactionRepository.existsById(id)) {

            transactionRepository.deleteById(id);

            return true;
        }

        return false;}



    public Transaction updateTransaction(Long id , Transaction updatedTransaction){

        Transaction existingTransaction =
                transactionRepository.findById(id).orElse(null);

        if(existingTransaction == null) {
            return null;
        }

        existingTransaction.setCustomerName(
                updatedTransaction.getCustomerName());

        existingTransaction.setAmount(
                updatedTransaction.getAmount());

        existingTransaction.setState(
                updatedTransaction.getState());

        existingTransaction.setStatus(
                updatedTransaction.getStatus());

        return transactionRepository.save(existingTransaction);}
}