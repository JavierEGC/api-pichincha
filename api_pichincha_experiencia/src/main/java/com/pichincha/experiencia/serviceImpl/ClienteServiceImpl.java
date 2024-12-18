package com.pichincha.experiencia.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pichincha.experiencia.model.Cliente;
import com.pichincha.experiencia.service.ClienteService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{

	private final WebClient webClient = WebClient.create("http://localhost:8080/api/cliente");
	
	public Flux<Cliente> listarCliente(){

		Flux<Cliente> clientes = webClient.get()
				.retrieve()
				.bodyToFlux(Cliente.class);

		return clientes;
	}

	@Override
	public Mono<Cliente> registrarCliente(Cliente cliente) {
		return webClient.post()
	            .bodyValue(cliente) 
	            .retrieve()
	            .bodyToMono(Cliente.class); 
	}
	
	@Override
	public Mono<Void> eliminarCliente(Long id){
		return webClient.delete()
				.uri("/{id}", id) 
	            .retrieve()
	            .bodyToMono(Void.class); 
	}

	@Override
	public Mono<Cliente> actualizarCliente(Cliente cliente, Long id) {
		return webClient.put()
				.uri("/{id}", id) 
	            .bodyValue(cliente) 
	            .retrieve()
	            .bodyToMono(Cliente.class); 
	}
	
}
