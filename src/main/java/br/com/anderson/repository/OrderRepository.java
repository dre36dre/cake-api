package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
