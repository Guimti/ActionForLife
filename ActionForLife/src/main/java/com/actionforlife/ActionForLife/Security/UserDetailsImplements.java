package com.actionforlife.ActionForLife.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.actionforlife.ActionForLife.Model.UserModel;

public class UserDetailsImplements implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha ;
	private List<GrantedAuthority> autorizacoes;
	public UserDetailsImplements(UserModel usuario) {
		this.email = usuario.getEmail();
		this.senha = usuario.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
