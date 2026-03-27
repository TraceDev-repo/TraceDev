package com.example.infrastructure;

import com.example.domain.TouristAccount;
import java.util.Optional;

/**
 * Port interface for tourist account repository.
 */
public interface ITouristAccountRepository {
    Optional<TouristAccount> findById(String accountId);
    void save(TouristAccount account);
}