package com.nullnumber1.testtaskifuture.service.impl;

import com.nullnumber1.testtaskifuture.model.Account;
import com.nullnumber1.testtaskifuture.repository.AccountRepository;
import com.nullnumber1.testtaskifuture.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Long getAmount(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            log.info("Balance with id {} not found", id);
            throw new IllegalArgumentException("Balance with id " + id + " not found");
        }
        return account.getValue();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(value = "accounts", key = "#id")
    public void addAmount(Integer id, Long value) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            log.info("Balance with id {} not found, creating new with this identifier", id);
            account = new Account();
            account.setId(id);
            account.setValue(value);
            accountRepository.save(account);
            return;
        }
        account.setValue(account.getValue() + value);
        accountRepository.save(account);
    }
}

