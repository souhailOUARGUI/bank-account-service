package org.example.bankaccountservice.service;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import org.example.bankaccountservice.dto.BankAccountRequestDTO;
import org.example.bankaccountservice.dto.BankAccountResponseDTO;
import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.mappers.AccountMapper;
import org.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{


    private BankAccountRepository bankAccountRepository;

    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper){
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
      BankAccount bankAccount = BankAccount.builder().
                id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .balance(bankAccountDTO.getBalance()).build();

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);



        return bankAccountResponseDTO;
    }
}
