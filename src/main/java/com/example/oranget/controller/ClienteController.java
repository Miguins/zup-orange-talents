package com.example.oranget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oranget.model.Cliente;
import com.example.oranget.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> novo(@RequestBody Cliente cliente) {
		
		boolean validEmailAddressRegex = clienteService.isEmailValido(cliente.getEmail());
		
		if (validEmailAddressRegex == false) {
			throw new RuntimeException("O Email é inválido");
		}
		
		return ResponseEntity.ok(clienteService.novo(cliente));
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.ok(clienteService.getClientes());
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(clienteService.getClienteById(id).orElse(null));
	}
	
	@GetMapping("/clienteByEmail/{email}")
	public ResponseEntity<Cliente> findByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(clienteService.getClienteByEmail(email));
	}
}
