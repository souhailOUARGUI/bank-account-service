package org.example.bankaccountservice.repositories;

import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
 List<BankAccount> findByType(AccountType type);
}
