package com.kintsugi.MiniKintsugi.controller;

import com.kintsugi.MiniKintsugi.dto.TransactionResponseDTO;
import com.kintsugi.MiniKintsugi.model.Transaction;
import com.kintsugi.MiniKintsugi.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.kintsugi.MiniKintsugi.dto.TransactionRequestDTO;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    } // this is DI

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction( dto));

    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable long id){
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTransaction(@PathVariable Long id){
        return transactionService.deleteTransaction(id);
    }


    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id ,
                                         @Valid @RequestBody Transaction updatedTransaction){

        return transactionService.updateTransaction(id,updatedTransaction);
    }

}
