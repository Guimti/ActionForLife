package com.actionforlife.ActionForLife.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.actionforlife.ActionForLife.Model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

	public List<CategoryModel> findAllByTypeContainingIgnoreCase(String type);

}