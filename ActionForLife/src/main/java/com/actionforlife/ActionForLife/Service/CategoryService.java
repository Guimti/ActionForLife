package com.actionforlife.ActionForLife.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.CategoryModel;
import com.actionforlife.ActionForLife.Repository.CategoryRepository;

@Service
public class CategoryService {
	private @Autowired CategoryRepository repository;

	public Optional<CategoryModel> updateCategory(CategoryModel categoryToChange) {
		return repository.findById(categoryToChange.getIdCategory()).map(existingCategory -> {
			existingCategory.setIdCategory(categoryToChange.getIdCategory());
			existingCategory.setType(categoryToChange.getType());
			existingCategory.setDescription(categoryToChange.getDescription());
			existingCategory.setRelevancy(categoryToChange.getRelevancy());
			return Optional.ofNullable(repository.save(existingCategory));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
