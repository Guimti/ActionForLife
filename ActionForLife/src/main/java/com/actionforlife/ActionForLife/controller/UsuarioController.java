package com.actionforlife.ActionForLife.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.actionforlife.ActionForLife.Model.Util.UsuarioLogin;
import com.actionforlife.ActionForLife.Repository.UsuarioRepository;
import com.actionforlife.ActionForLife.Service.UserService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;
	private @Autowired UserService service;

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

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> cadastrarUsuario(@RequestBody UsuarioModel novoUsuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(novoUsuario));

	}

	@PutMapping("logar")
	public ResponseEntity<UsuarioLogin> Autorization(@RequestBody Optional<UsuarioLogin> user) {
		return service.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
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