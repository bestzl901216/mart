package com.firm.wham.adapter.web;

import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ricardo zhou
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountProvider accountProvider;

    @PostMapping(value = "/signIn")
    public Response signIn(@RequestBody AccountSignInCmd cmd) {
        return accountProvider.signIn(cmd);
    }
}
