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
import com.actionforlife.ActionForLife.Model.UsuarioModel;
import com.actionforlife.ActionForLife.Repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> pegarTodos() {
		List<UsuarioModel> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UsuarioModel> encontrarPorId(@PathVariable Long idUsuario) {

		return repository.findById(idUsuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable(value = "nome") String nome) {
		List<UsuarioModel> objetoLista = repository.findAllByNomeContainingIgnoreCase(nome);
		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/adicionar")
	public ResponseEntity<UsuarioModel> adicionarUsuario(@Valid @RequestBody UsuarioModel novoUsuario) {
		return ResponseEntity.ok(repository.save(novoUsuario));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> atualizarUsuario(@Valid @RequestBody UsuarioModel usuarioAtualizado) {
		return ResponseEntity.ok(repository.save(usuarioAtualizado));
	}

	@DeleteMapping("/deletar/{id}")
	public void deletarPorId(@PathVariable(value = "id") Long idUsuario) {
		repository.deleteById(idUsuario);
	}
}