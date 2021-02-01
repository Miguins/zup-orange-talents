package com.example.oranget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oranget.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);

}
