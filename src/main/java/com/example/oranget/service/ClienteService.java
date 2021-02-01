package com.example.oranget.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oranget.model.Aposta;
import com.example.oranget.model.Cliente;
import com.example.oranget.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ApostaService apostaService;
	
	public Cliente novo(Cliente cliente) {
		
		Cliente validar = this.validar(cliente);
		
		if (validar != null) {
			return validar;
		} else {
			
			Aposta aposta = new Aposta();
			aposta.agora()
			.cliente(cliente)
			.gerarNumero();
			cliente.getApostas().add(aposta);
			
			this.clienteRepository.save(cliente);
			this.apostaService.nova(aposta);
			
			return cliente;			
		}
		
	}
	
	public Optional<Cliente> getClienteById(Long id) {
		return this.clienteRepository.findById(id);
	}
	
	public Cliente getClienteByEmail(String email) {
		return this.clienteRepository.findByEmail(email);
	}
	
	public List<Cliente> getClientes() {
		return this.clienteRepository.findAll();
	}
	
	private Cliente validar(Cliente cliente) {
		Cliente clienteCadastrado = this.clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteCadastrado != null) {
			Aposta aposta = new Aposta();
			aposta.agora()
					.cliente(clienteCadastrado)
						.gerarNumero();
			this.apostaService.nova(aposta);
		}
		
		return clienteCadastrado;
	}
	
	public boolean isEmailValido(String email) {
	    boolean isEmailValido = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            isEmailValido = true;
	        }
	    }
	    return isEmailValido;
	}
}
