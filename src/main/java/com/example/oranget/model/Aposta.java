package com.example.oranget.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "APOSTA")
public class Aposta {
	
	@Id
	@Column(name = "ID_APOSTA")
	@SequenceGenerator(name = "SQ_APOSTA", sequenceName = "SQ_APOSTA", allocationSize = 1)
	@GeneratedValue(generator = "SQ_APOSTA", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NUMERO")
	private Integer numero;
	
	@Column(name = "DT_APOSTA", nullable = false)
	private LocalDateTime dataAposta;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	@JsonBackReference
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public LocalDateTime getDataAposta() {
		return dataAposta;
	}

	public void setDataAposta(LocalDateTime dataAposta) {
		this.dataAposta = dataAposta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Aposta agora() {
		this.setDataAposta(LocalDateTime.now());
		return this;
	}
	
	public Aposta cliente(Cliente cliente) {
		this.setCliente(cliente);
		return this;
	}
	
	public Aposta gerarNumero() {
		int[] numbers = new int[1];       

		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = (int)(Math.random()*100 + 1);
		}
		
		this.setNumero(numbers[0]);
		
		return this;
	}
}
