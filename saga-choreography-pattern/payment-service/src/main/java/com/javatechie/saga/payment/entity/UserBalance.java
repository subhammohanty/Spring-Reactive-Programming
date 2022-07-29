package com.javatechie.saga.payment.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {

    @Id
    private int userId;
    private int price;
}
