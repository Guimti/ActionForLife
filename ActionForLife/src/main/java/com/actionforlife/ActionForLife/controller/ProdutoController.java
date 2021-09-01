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

import com.actionforlife.ActionForLife.Model.ProdutoModel;
import com.actionforlife.ActionForLife.Repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
/*
	@GetMapping("/todos")
	public ResponseEntity<List<ProdutoModel>> encontrarTodos() {

		return ResponseEntity.ok(repository.findAll());
	}
*/

	@GetMapping("/todos")
	public ResponseEntity<List<ProdutoModel>> pegarTodos() {
		List<ProdutoModel> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ProdutoModel> encontrarPorId(@PathVariable Long idProduto) {

		return repository.findById(idProduto).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoModel>> buscarPorNome(@PathVariable(value = "nome") String nome) {
		List<ProdutoModel> objetoLista = repository.findAllByNomeContainingIgnoreCase(nome);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/adicionar")
	public ResponseEntity<ProdutoModel> adicionarProduto(@Valid @RequestBody ProdutoModel novoProduto) {
		return ResponseEntity.ok(repository.save(novoProduto));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<ProdutoModel> atualizarProduto(@Valid @RequestBody ProdutoModel produtoAtualizado) {
		return ResponseEntity.ok(repository.save(produtoAtualizado));
	}

	@DeleteMapping("/deletar/{id}")
	public void deletarPorId(@PathVariable(value = "id") Long idProduto) {
		repository.deleteById(idProduto);
	}

}
