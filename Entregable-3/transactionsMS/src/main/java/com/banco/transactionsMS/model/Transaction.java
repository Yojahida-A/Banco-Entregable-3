package com.banco.transactionsMS.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private String type; // "Deposit", "Withdrawal", "Transfer"
    private Double amount;
    private LocalDateTime date;
    private String sourceAccount;
    private String destinationAccount;
}

