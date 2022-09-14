package com.firm.wham.infrastructure.account;

import com.firm.wham.domain.security.Authentication;
import com.firm.wham.domain.security.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ricardo zhou
 */
@Repository
@RequiredArgsConstructor
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final Map<String, Authentication> map = new HashMap<>();

    @Override
    public void add(Authentication authentication) {
        map.put(authentication.getAccountName(), authentication);
    }

    @Override
    public Optional<Authentication> find(String accountName) {
        return Optional.ofNullable(map.get(accountName));
    }
}
