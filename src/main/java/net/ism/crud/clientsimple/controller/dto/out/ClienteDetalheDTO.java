package net.ism.crud.clientsimple.controller.dto.out;

import java.util.List;
import java.util.stream.Collectors;

import net.ism.crud.clientsimple.model.Cliente;

public class ClienteDetalheDTO {
	
	private Long id;
	private String nome;
	private Integer idade;
	
	public ClienteDetalheDTO() {
		
	}
	public ClienteDetalheDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.idade = cliente.getIdade();
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Integer getIdade() {
		return idade;
	}

	public static List<ClienteDetalheDTO> toList(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDetalheDTO::new).collect(Collectors.toList());
	}
}
