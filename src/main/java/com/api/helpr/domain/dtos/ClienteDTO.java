package com.api.helpr.domain.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.api.helpr.domain.Cliente;
import com.api.helpr.domain.Pessoa;
import com.api.helpr.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO extends Pessoa {
	
	// Precisa ser inserido em toda classe
	private static final long serialVersionUID = 1L;

	
	protected Integer id;
	@NotNull (message = "O campo Nome não deve ser nulo")
	protected String nome;
	@NotNull (message = "O campo CPF não deve ser nulo")
	protected String cpf;
	@NotNull (message = "O campo email não deve ser nulo")
	protected String email;
	@NotNull (message = "O campo Senha não deve ser nulo")
	protected String senha;
	// instanciando, reservando um espaço na memoria
	protected Set<Integer> perfils = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy") //para formatar a data
	protected LocalDate dataCriacao = LocalDate.now();

	public ClienteDTO() {
		super();
		// acessa na classe pessoa
		addPerfils(Perfil.CLIENTE);
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		// Collectors toSet() retorna um Collector que acumula os elementos de entrada em um novo Set.
		this.perfils = obj.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
		// acessa o get da classe
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// muda para ser dotipo perfil
	public Set<Perfil> getPerfils() {
		return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
   
	// muda referencia de set para add
	public void addPerfil(Perfil perfil) {
		this.perfils.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
}










