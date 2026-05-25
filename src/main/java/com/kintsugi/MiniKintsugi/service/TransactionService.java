package com.kintsugi.MiniKintsugi.service;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kintsugi.MiniKintsugi.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.kintsugi.MiniKintsugi.model.Transaction;
import com.kintsugi.MiniKintsugi.dto.TransactionRequestDTO;
import com.kintsugi.MiniKintsugi.dto.TransactionResponseDTO;
import org.springframework.web.bind.annotation.*;

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

        logger.info(
                "Transaction created with ID: {}",
                savedTransaction.getId()
        );
        return responseDTO;

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