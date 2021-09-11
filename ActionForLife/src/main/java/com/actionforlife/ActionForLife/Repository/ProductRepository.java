package com.actionforlife.ActionForLife.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actionforlife.ActionForLife.Model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

	public List<ProductModel> findAllByNameContainingIgnoreCase(String name);
}