package com.firm.wham.domain.customer.gateway;

import com.firm.wham.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
