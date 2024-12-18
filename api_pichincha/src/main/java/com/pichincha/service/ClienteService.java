package com.pichincha.service;

import org.springframework.http.ResponseEntity;

import com.pichincha.model.Cliente;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClienteService {

	public Mono<ResponseEntity<Cliente>> registrarCliente(Cliente cliente);

	public Mono<ResponseEntity<Flux<Cliente>>> listarCliente();
	
	public Mono<ResponseEntity<Cliente>> actualizarCliente(Cliente cliente, Long id);
	
	public Mono<ResponseEntity<Void>> eliminarCliente(Long id);

}
