package com.javatechie.saga.payment.repository;

import com.javatechie.saga.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepo extends JpaRepository<UserTransaction , Integer> {
}
