package com.nullnumber1.testtaskifuture.model;

import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Data
@CacheConfig(cacheNames = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "balance")
    private Long value;
}
