package com.actionforlife.ActionForLife.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actionforlife.ActionForLife.Model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	public List<ProdutoModel> findAllByNomeContainingIgnoreCase(String nome);
}
