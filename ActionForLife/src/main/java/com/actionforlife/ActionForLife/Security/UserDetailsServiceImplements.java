package com.actionforlife.ActionForLife.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.UserModel;
import com.actionforlife.ActionForLife.Repository.UserRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserModel> objectOptional = repository.findByEmail(username);

		if (objectOptional.isPresent()) {
			return new UserDetailsImplements(objectOptional.get());
		} else {
			throw new UsernameNotFoundException(username + "Não Existe!");
		}
	}
}
