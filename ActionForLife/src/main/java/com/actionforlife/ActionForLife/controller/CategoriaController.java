package com.actionforlife.ActionForLife.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.actionforlife.ActionForLife.Model.CategoriaModel;
import com.actionforlife.ActionForLife.Repository.CategoriaRepository;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> GetAll(){
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<List> GetById (@PathVariable long id){
		
		return repository.findById(id)
		
	}
	
	
	
	
	
}
