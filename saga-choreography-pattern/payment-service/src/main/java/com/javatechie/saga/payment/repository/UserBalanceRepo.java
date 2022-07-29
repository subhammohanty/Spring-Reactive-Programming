package com.javatechie.saga.payment.repository;

import com.javatechie.saga.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepo extends JpaRepository<UserBalance , Integer> {
}
