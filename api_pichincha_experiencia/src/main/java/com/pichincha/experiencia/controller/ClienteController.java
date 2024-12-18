package com.pichincha.experiencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.experiencia.model.Cliente;
import com.pichincha.experiencia.service.ClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/experiencia/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public Flux<Cliente> listaClient() {
		return clienteService.listarCliente();
	}
	
	@PostMapping
    public Mono<Cliente> registraClient(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }
	
	@PutMapping("/{id}")
    public Mono<Cliente> registraClient(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.actualizarCliente(cliente, id);
    }
	
	@DeleteMapping("/{id}")
	public Mono<Void> eliminaClient(@PathVariable Long id) {
        return clienteService.eliminarCliente(id);
    }

}
