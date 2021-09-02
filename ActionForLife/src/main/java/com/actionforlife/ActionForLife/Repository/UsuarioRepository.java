package com.actionforlife.ActionForLife.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.actionforlife.ActionForLife.Model.UsuarioModel;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);
	List<UsuarioModel> findAllByemail(String email);
}
