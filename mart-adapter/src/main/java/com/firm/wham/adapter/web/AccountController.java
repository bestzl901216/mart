package com.firm.wham.adapter.web;

import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ricardo zhou
 */
@RestController
public class AccountController {

    private AccountProvider accountProvider;

    @GetMapping(value = "/helloWorld")
    public String helloWorld() {
        return "Hello, welcome to COLA world!";
    }

    @PostMapping(value = "/customer")
    public Response listCustomerByName(AccountSignInCmd cmd) {
        return accountProvider.signIn(cmd);
    }
}
