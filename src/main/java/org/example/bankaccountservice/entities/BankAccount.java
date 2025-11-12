package org.example.bankaccountservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankaccountservice.enums.AccountType;

import java.util.Date;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id

    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    private AccountType type;



}
