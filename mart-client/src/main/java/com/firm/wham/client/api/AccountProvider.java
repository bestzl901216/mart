package com.firm.wham.client.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.client.dto.AccountSignOutCmd;
import com.firm.wham.client.dto.AccountSignUpCmd;

/**
 * 账号服务
 *
 * @author ricardo zhou
 */
public interface AccountProvider {

    /**
     * 注册
     *
     * @param cmd 指令
     * @return 相应
     */
    Response signUp(AccountSignUpCmd cmd);

    /**
     * 登录
     *
     * @param cmd 指令
     * @return 相应
     */
    SingleResponse<String> signIn(AccountSignInCmd cmd);

    /**
     * 登出
     *
     * @param cmd 指令
     * @return 相应
     */
    Response signOut(AccountSignOutCmd cmd);

}