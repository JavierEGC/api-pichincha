package com.pichincha.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pichincha.model.Cliente;
import com.pichincha.repository.ClienteRepository;
import com.pichincha.service.ClienteService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	private final WebClient webClient = WebClient.create("https://gorest.co.in/public/v2");
		
    public Mono<ResponseEntity<Cliente>> registrarCliente(Cliente cliente) {
    	    	
        List<?> clientes = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(List.class)
                .block(); 

        boolean clientExists = clientes.stream()
                .anyMatch(cli -> {
                    var userMap = (java.util.Map<String, Object>) cli;
                    return cliente.getName().equals(userMap.get("name")) &&
                           cliente.getEmail().equals(userMap.get("email"));
                });
        
        	if (clientExists) {
                cliente.setStatus("exists");
            } else {
                cliente.setStatus("active");
            }        	
        	
        return clienteRepository.save(cliente)
        		.map(cli -> ResponseEntity.status(HttpStatus.CREATED).body(cli));

    }
    
    
    public Mono<ResponseEntity<Flux<Cliente>>> listarCliente(){
    	Flux<Cliente> clientes = clienteRepository.findAll();

        return clientes.hasElements()
                .flatMap(hasElements -> {
                    if (hasElements) {
                        return Mono.just(ResponseEntity.ok(clientes));
                    } else {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                });
    }


	@Override
	public Mono<ResponseEntity<Cliente>> actualizarCliente(Cliente cliente, Long id) {
		
		Mono<Cliente> cli = clienteRepository.findById(id);

		return cli
		    .flatMap(clienteOpt -> {
		        if (clienteOpt != null) {
		        	clienteOpt.setName(cliente.getName());
		        	clienteOpt.setEmail(cliente.getEmail());
		        	clienteOpt.setGender(cliente.getGender());
		        	clienteOpt.setStatus(cliente.getStatus());
		        	return clienteRepository.save(clienteOpt)
		                    .map(updatedCliente -> ResponseEntity.ok(updatedCliente)); 
		        } else {
		            return Mono.just(ResponseEntity.notFound().build());
		        }
		    });
		   
	}


	@Override
	public Mono<ResponseEntity<Void>> eliminarCliente(Long id) {
		 
		Mono<Cliente> cli = clienteRepository.findById(id);
		
		return cli
			    .flatMap(clienteOpt -> {
			        if (clienteOpt != null) {
			        	return clienteRepository.delete(clienteOpt)
			        			.then(Mono.just(ResponseEntity.noContent().build())); 
			        } else {
			            return Mono.just(ResponseEntity.notFound().build());
			        }
			    });
	}


    


}
