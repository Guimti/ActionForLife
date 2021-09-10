package com.actionforlife.ActionForLife.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.UsuarioModel;
import com.actionforlife.ActionForLife.Model.Util.UsuarioLogin;
import com.actionforlife.ActionForLife.Repository.UsuarioRepository;


@Service
public class UserService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Object> cadastrarUsuario (UsuarioModel user) {
		return repository.findByEmail(user.getEmail()).map(emailExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getSenha());
			user.setSenha(passwordEncoder);
			return Optional.ofNullable(repository.save(user));
		});
	}
	
	public Optional<UsuarioLogin> logar (Optional<UsuarioLogin> user ){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = repository.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				
				return user;
				
			}
		
		

		
	}
		return null;
	
	}
	
	
}
