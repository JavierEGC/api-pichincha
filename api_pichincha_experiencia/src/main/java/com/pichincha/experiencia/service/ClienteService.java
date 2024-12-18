package com.pichincha.experiencia.service;

import com.pichincha.experiencia.model.Cliente;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

	public Flux<Cliente> listarCliente();
	
	public Mono<Cliente> registrarCliente(Cliente cliente);
	
	public Mono<Cliente> actualizarCliente(Cliente cliente, Long id);
	
	public Mono<Void> eliminarCliente(Long id);
}
