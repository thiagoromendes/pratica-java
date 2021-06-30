package com.evoluum.java.evoluumjava.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoluum.java.evoluumjava.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	
	Categoria findByNome(String nome);

}
