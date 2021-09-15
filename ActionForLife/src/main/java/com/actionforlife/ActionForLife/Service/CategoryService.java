package com.actionforlife.ActionForLife.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.CategoryModel;
import com.actionforlife.ActionForLife.Repository.CategoryRepository;

@Service
public class CategoryService {
	private @Autowired CategoryRepository repository;

	public Optional<CategoryModel> UpdateCategory(CategoryModel categoryToChange) {
		return repository.findById(categoryToChange.getIdCategory()).map(ExistingCategory -> {
			ExistingCategory.setIdCategory(categoryToChange.getIdCategory());
			return Optional.ofNullable(repository.save(ExistingCategory));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
