package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entity.Cake;

public interface CakeRepository extends JpaRepository<Cake, Long> {

}
