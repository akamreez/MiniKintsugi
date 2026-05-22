package com.kintsugi.MiniKintsugi.repository;
import com.kintsugi.MiniKintsugi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
