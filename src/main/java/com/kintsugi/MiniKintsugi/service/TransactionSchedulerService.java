package com.kintsugi.MiniKintsugi.service;

import com.kintsugi.MiniKintsugi.model.Transaction;
import com.kintsugi.MiniKintsugi.model.TransactionStatus;
import com.kintsugi.MiniKintsugi.repository.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionSchedulerService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    TransactionSchedulerService.class
            );

    private final TransactionRepository
            transactionRepository;

    public TransactionSchedulerService(
            TransactionRepository transactionRepository
    ) {
        this.transactionRepository =
                transactionRepository;
    }

   // @Scheduled(fixedRate = 30000)
    public void processPendingTransactions() {

        logger.info(
                "Scheduler checking pending transactions..."
        );

        List<Transaction> pendingTransactions =
                transactionRepository.findAll();

        for(Transaction transaction :
                pendingTransactions) {

            if(transaction.getStatus() ==
                    TransactionStatus.PENDING) {

                transaction.setStatus(
                        TransactionStatus.COMPLETED
                );

                transactionRepository.save(
                        transaction
                );

                logger.info(
                        "Transaction {} marked COMPLETED",
                        transaction.getId()
                );

                logger.info(
                        "EVENT: TransactionCompleted published for transaction {}",
                        transaction.getId()
                ); // event driven architecture means when a operation is completed in one service
                // it notifies the other service , thats what  event driven architecture means ,
                // they say event is emitted , means event is completed
            }
        }
    }
}
