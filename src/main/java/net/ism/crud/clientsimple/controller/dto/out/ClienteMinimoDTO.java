package net.ism.crud.clientsimple.controller.dto.out;

import java.util.List;
import java.util.stream.Collectors;

import net.ism.crud.clientsimple.model.Cliente;

public class ClienteMinimoDTO {
	
	private Long id;
	
	public ClienteMinimoDTO() {
		
	}
	public ClienteMinimoDTO(Cliente cliente) {
		this.id = cliente.getId();
	}
	
	public Long getId() {
		return id;
	}

	public static List<ClienteMinimoDTO> toList(List<Cliente> clientes) {
		return clientes.stream().map(ClienteMinimoDTO::new).collect(Collectors.toList());
	}
}
