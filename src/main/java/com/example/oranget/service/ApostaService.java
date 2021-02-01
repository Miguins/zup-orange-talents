package com.example.oranget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oranget.model.Aposta;
import com.example.oranget.repository.ApostaRepository;

@Service
public class ApostaService {

	@Autowired
	ApostaRepository apostaRepository;
	
	public Aposta nova(Aposta aposta) {
		return this.apostaRepository.save(aposta);
	}
}
