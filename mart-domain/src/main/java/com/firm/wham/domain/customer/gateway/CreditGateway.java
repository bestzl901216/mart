package com.firm.wham.domain.customer.gateway;

import com.firm.wham.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
