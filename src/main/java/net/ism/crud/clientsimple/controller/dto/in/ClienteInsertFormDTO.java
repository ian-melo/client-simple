package net.ism.crud.clientsimple.controller.dto.in;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.ism.crud.clientsimple.model.Cliente;

public class ClienteInsertFormDTO {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
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

	public Cliente toModel() {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setIdade(this.idade);
		return cliente;
	}
}
