package net.ism.crud.clientsimple.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.ism.crud.clientsimple.controller.dto.in.ClienteInsertFormDTO;
import net.ism.crud.clientsimple.controller.dto.in.ClienteUpdateFormDTO;
import net.ism.crud.clientsimple.controller.dto.out.ClienteDetalheDTO;
import net.ism.crud.clientsimple.controller.dto.out.ClienteMinimoDTO;
import net.ism.crud.clientsimple.model.Cliente;
import net.ism.crud.clientsimple.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EstatisticaController estatisticaController;
	
	
	@GetMapping
	public ResponseEntity<List<ClienteMinimoDTO>> listar() {
		// Lista clientes
		List<Cliente> clientes = clienteRepo.findAll();
		// Retorno
		return ResponseEntity.ok(ClienteMinimoDTO.toList(clientes));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDetalheDTO> detalhar(@PathVariable Long id) {
		// Valida
		if(!existsCliente(id)) return ResponseEntity.notFound().build();
		// Busca cliente
		Cliente cliente = clienteRepo.getOne(id);
		// Retorno
		return ResponseEntity.ok(new ClienteDetalheDTO(cliente));
	}
	
	@PostMapping @Transactional
	public ResponseEntity<ClienteDetalheDTO> inserir(@RequestBody @Valid ClienteInsertFormDTO input, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
		// Data
		LocalDate agora = LocalDate.now();
		// Insere cliente
		Cliente cliente = input.toModel();
		clienteRepo.save(cliente);
		// Insere estatistica
		estatisticaController.inserir(request.getRemoteAddr(), agora, cliente);
		// Retorno
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDetalheDTO(cliente));
	}
	@PutMapping("/{id}") @Transactional
	public ResponseEntity<ClienteDetalheDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteUpdateFormDTO input) {
		// Valida
		if(!existsCliente(id)) return ResponseEntity.notFound().build();
		// Atualiza
		Cliente cliente = input.update(id, clienteRepo);
		// Retorno
		return ResponseEntity.ok(new ClienteDetalheDTO(cliente));
	}
	@DeleteMapping("/{id}") @Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		// Valida
		if(!existsCliente(id)) return ResponseEntity.notFound().build();
		// Remove estatistica
		boolean result = estatisticaController.remover(id);
		if(result == EstatisticaController.OPERATION_FAIL) return ResponseEntity.status(500).build();
		// Remove cliente
		clienteRepo.deleteById(id);
		// Retorno
		return ResponseEntity.ok().build();
	}
	
	private boolean existsCliente(Long id) {
		return clienteRepo.existsById(id);
	}

}
