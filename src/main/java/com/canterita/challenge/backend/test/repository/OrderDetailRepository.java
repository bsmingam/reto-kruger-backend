package com.canterita.challenge.backend.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

}
