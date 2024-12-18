package com.pichincha.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.model.Cliente;
import com.pichincha.service.ClienteService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;

	@PostMapping
    public Mono<ResponseEntity<Cliente>> registraClient(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Cliente>>> listaClient(){
		return clienteService.listarCliente();
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Cliente>> actualizaClient(@RequestBody Cliente cliente, @PathVariable Long id){
		return clienteService.actualizarCliente(cliente, id);
	}
	
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> actualizaClient(@PathVariable Long id){
		return clienteService.eliminarCliente(id);
	}
	
}
