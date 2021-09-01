package com.actionforlife.ActionForLife.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actionforlife.ActionForLife.Model.CategoriaModel;
import com.actionforlife.ActionForLife.Repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<CategoriaModel>> GetAll() {

		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<CategoriaModel> GetById(@PathVariable Long id) {

		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/salvar")
	public ResponseEntity<CategoriaModel> salvar(@Valid @RequestBody CategoriaModel paraSalvar) {
		return ResponseEntity.ok(repository.save(paraSalvar));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<CategoriaModel> atualizar(@Valid @RequestBody CategoriaModel paraAtualizar)
	{
		return ResponseEntity.ok(repository.save(paraAtualizar));
	}
	
	@DeleteMapping("/deletar/{deletarId}")
	public void deletarId(@PathVariable (value = "deletarId") Long paraDeletar)
	{
		repository.deleteById(paraDeletar);
	}
	
	@GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CategoriaModel>> buscarPorTipo(@PathVariable (value = "tipo") String tipo) {
        List<CategoriaModel> objetoLista = repository.findAllByTipoContainingIgnoreCase(tipo);
        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }
}









