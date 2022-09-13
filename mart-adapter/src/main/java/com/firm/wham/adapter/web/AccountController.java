package com.firm.wham.adapter.web;

import com.alibaba.cola.dto.SingleResponse;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ricardo zhou
 */
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountProvider accountProvider;

    @PostMapping("helloWorld")
    public SingleResponse<String> helloWorld() {
        return SingleResponse.of("hello world");
    }

    @PostMapping("signIn")
    public SingleResponse<String> signIn(@RequestBody AccountSignInCmd cmd) {
        return accountProvider.signIn(cmd);
    }
}
