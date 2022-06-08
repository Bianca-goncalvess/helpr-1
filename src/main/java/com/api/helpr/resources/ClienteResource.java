package com.api.helpr.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.dtos.ClienteDTO;
import com.api.helpr.services.ClienteService;

@RestController
// caminho para acessar essa classe
@RequestMapping(value = "/service/clientes")
public class ClienteResource {
	
	@Autowired // se comunica com cliente service
	private ClienteService service;
	
	// get acessa o id
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		// solicita e recebe a informação do service
		Cliente obj = service.findById(id);
		// retornando para o front atráves do dto como metodo de segurança
	return ResponseEntity.ok().body(new ClienteDTO(obj));
	}

}
