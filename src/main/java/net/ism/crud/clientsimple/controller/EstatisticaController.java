package net.ism.crud.clientsimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.ism.crud.clientsimple.model.Cliente;
import net.ism.crud.clientsimple.repository.EstatisticaRepository;

@Controller
public class EstatisticaController {
	
	@Autowired
	private EstatisticaRepository estatisticaRepo;
	
	public void inserir(String ip, Cliente cliente) {
		
	}
	
	public void remover(Long clienteId) {
		
	}
	
}
