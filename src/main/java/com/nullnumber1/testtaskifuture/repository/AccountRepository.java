package com.nullnumber1.testtaskifuture.repository;

import com.nullnumber1.testtaskifuture.model.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Cacheable({"accounts"})
    Optional<Account> findById(Integer id);
}
