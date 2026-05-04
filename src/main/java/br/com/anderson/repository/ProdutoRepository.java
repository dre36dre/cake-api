package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
