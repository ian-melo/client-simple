package net.ism.crud.clientsimple.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public ResponseEntity<String> exibir() {
		String txt = "Isso funciona!<br><br>Documentação: <a href='https://github.com/ian-melo/client-simple'>https://github.com/ian-melo/client-simple</a>";
		return ResponseEntity.ok(txt);
	}
}
