package com.actionforlife.ActionForLife.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.UserModel;
import com.actionforlife.ActionForLife.Model.Util.UserLogin;
import com.actionforlife.ActionForLife.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Optional<Object> registerUser(UserModel user) {
		return repository.findByEmail(user.getEmail()).map(emailExists -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getPassword());
			user.setPassword(passwordEncoder);
			return Optional.ofNullable(repository.save(user));
		});
	}

	public Optional<UserLogin> authorize(Optional<UserLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UserModel> userEmail = repository.findByEmail(user.get().getEmail());

		if (userEmail.isPresent()) {
			if (encoder.matches(user.get().getPassword(), userEmail.get().getPassword())) {

				String auth = user.get().getEmail() + ":" + user.get().getPassword();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(userEmail.get().getId());
				user.get().setName(userEmail.get().getName());
				user.get().setLastName(userEmail.get().getLastName());
				user.get().setEmail(userEmail.get().getEmail());
				user.get().setAddress(userEmail.get().getAddress());
				user.get().setCpf(userEmail.get().getCpf());

				return user;
			}
		}
		return Optional.empty();
	}
	
	public Optional<?> update(UserModel user) {
		return repository.findById(user.getIdUser()).map(userExists -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getPassword());

			userExists.setName(user.getName());
			userExists.setEmail(user.getEmail());
			userExists.setPassword(passwordEncoder);

			return Optional.ofNullable(repository.save(userExists));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
