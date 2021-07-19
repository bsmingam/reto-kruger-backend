package com.canterita.challenge.backend.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.canterita.challenge.backend.test.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
