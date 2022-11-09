package com.nullnumber1.testtaskifuture.rest;

import com.nullnumber1.testtaskifuture.dto.AccountDto;
import com.nullnumber1.testtaskifuture.service.AccountService;
import com.nullnumber1.testtaskifuture.service.RequestCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/")
public class AccountRestControllerV1 {
    private final AccountService accountService;
    private final RequestCounterService requestCounterService;

    @Autowired
    public AccountRestControllerV1(AccountService accountService, RequestCounterService requestCounterService) {
        this.accountService = accountService;
        this.requestCounterService = requestCounterService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Long> getAmount(@PathVariable int id) {
        try {
            requestCounterService.increment();
            return new ResponseEntity<>(accountService.getAmount(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("add")
    public ResponseEntity<HttpStatus> addAmount(@RequestBody AccountDto accountDto) {
        requestCounterService.increment();
        if (accountDto.getId() == null || accountDto.getNewValue() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        accountService.addAmount(accountDto.getId(), accountDto.getNewValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
