package com.api.helpr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.Pessoa;
import com.api.helpr.domain.dtos.ClienteDTO;
import com.api.helpr.repositories.ClienteRepository;
import com.api.helpr.repositories.PessoaRepository;
import com.api.helpr.services.exceptions.DataIntegrityViolationException;
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
	
	// Busca para todos os registros de clientes
	public List<Cliente> findAllClientes(){
		return repository.findAll();
	}
	
	// Método que faea a criação de novo cliente
	public Cliente create(ClienteDTO objDto) {
		objDto.setId(null);
		validaCpfEEmail(objDto);
		Cliente newObj = new Cliente(objDto);
		return repository.save(newObj);
	}
	
	//Método para modificar técnicos existentes.
	public Cliente update(Integer id, ClienteDTO objDto) {
		objDto.setId(id);
		Cliente oldObj =findById(id);
		validaCpfEEmail(objDto);
		oldObj = new Cliente(objDto);
		return repository.save(oldObj);
	}
	
	//Método que deleta um cliente pelo id
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O cliente: " +id+ " Tem chamado no sistem: " +
		obj.getChamados().size());
		}
		repository.deleteById(id);
	}
	
	// Valida CPF e EMAIL para update e create
		private void validaCpfEEmail(ClienteDTO objDto) {
			Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
			if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
				throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
			}
			obj = pessoaRepository.findByEmail(objDto.getEmail());
			if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
				throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
			}
		}
}
