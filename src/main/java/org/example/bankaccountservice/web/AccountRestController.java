package org.example.bankaccountservice.web;


import org.example.bankaccountservice.entities.BankAccount;
import org.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/accounts")
    public List<BankAccount>  bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return  bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", id)));
    }


    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        if (bankAccount.getId() == null ) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update( @PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account with id %s not found", id)));
         bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getType() != null )bankAccount1.setType(bankAccount.getType());
        if (bankAccount.getCurrency() != null )bankAccount1.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt() != null ) bankAccount1.setCreatedAt(new Date());

        return bankAccountRepository.save(bankAccount1);

    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }




}
