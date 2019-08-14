package net.ism.crud.clientsimple.controller.dto.in;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import net.ism.crud.clientsimple.model.Cliente;
import net.ism.crud.clientsimple.repository.ClienteRepository;

public class ClienteUpdateFormDTO {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @Positive
	private Integer idade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Cliente update(Long id, ClienteRepository clienteRepo) {
		Cliente cliente = clienteRepo.getOne(id);
		cliente.setNome(this.nome);
		cliente.setIdade(this.idade);
		return cliente;
	}
}
