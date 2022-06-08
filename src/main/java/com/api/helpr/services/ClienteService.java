package com.api.helpr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpr.domain.Cliente;
import com.api.helpr.repositories.ClienteRepository;
import com.api.helpr.repositories.PessoaRepository;
import com.api.helpr.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired // Faz a conecção com o cliente repository
	private ClienteRepository repository;
	
	@Autowired // vinculo com o repository pessoa
	private PessoaRepository pessoaRepository;
	
	//Metodo de busca por umID no banco de dados
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Esse Objeto não foi encontrado" + id));
	}
	
	
}
