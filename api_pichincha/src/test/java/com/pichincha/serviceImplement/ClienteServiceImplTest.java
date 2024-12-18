package com.pichincha.serviceImplement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.pichincha.model.Cliente;
import com.pichincha.repository.ClienteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarCliente() {
        Cliente cliente = new Cliente(1L, "Javier Galarza", "javier.galarza@example.com", "male", "active");

        when(clienteRepository.save(cliente)).thenReturn(Mono.just(cliente));

        Mono<ResponseEntity<Cliente>> response = clienteService.registrarCliente(cliente);

        StepVerifier.create(response)
            .assertNext(res -> {
                assertEquals(HttpStatus.CREATED, res.getStatusCode());
                assertEquals("active", res.getBody().getStatus());
            })
            .verifyComplete();

        verify(clienteRepository).save(cliente);
    }

    @Test
    void testListarCliente() {
        Cliente cliente1 = new Cliente(1L, "Maria Calderon", "maria.calderon@example.com", "famela", "active");
        Cliente cliente2 = new Cliente(2L, "Javier Galarza", "javier.galarza@example.com", "male", "active");

        when(clienteRepository.findAll()).thenReturn(Flux.just(cliente1, cliente2));

        Mono<ResponseEntity<Flux<Cliente>>> response = clienteService.listarCliente();

        StepVerifier.create(response)
            .assertNext(res -> {
                assertEquals(HttpStatus.OK, res.getStatusCode());
                StepVerifier.create(res.getBody())
                    .expectNext(cliente1, cliente2)
                    .verifyComplete();
            })
            .verifyComplete();

        verify(clienteRepository).findAll();
    }

    @Test
    void testActualizarCliente() {
        Cliente existingCliente = new Cliente(1L, "Javier Galarza", "javier.galarza@example.com", "male", "active");
        Cliente updatedCliente = new Cliente(1L, "Javier Actualizado", "javier.actualizado@example.com", "male", "active");

        when(clienteRepository.findById(1L)).thenReturn(Mono.just(existingCliente));
        when(clienteRepository.save(existingCliente)).thenReturn(Mono.just(updatedCliente));

        Mono<ResponseEntity<Cliente>> response = clienteService.actualizarCliente(updatedCliente, 1L);

        StepVerifier.create(response)
            .assertNext(res -> {
                assertEquals(HttpStatus.OK, res.getStatusCode());
                assertEquals("Javier Actualizado", res.getBody().getName());
                assertEquals("javier.actualizado@example.com", res.getBody().getEmail());
            })
            .verifyComplete();

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(existingCliente);
    }

    @Test
    void testEliminarCliente() {
        Cliente existingCliente = new Cliente(1L, "Javier Galarza", "javier.galarza@example.com", "male", "active");

        when(clienteRepository.findById(1L)).thenReturn(Mono.just(existingCliente));
        when(clienteRepository.delete(existingCliente)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> response = clienteService.eliminarCliente(1L);

        StepVerifier.create(response)
            .assertNext(res -> assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode()))
            .verifyComplete();

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).delete(existingCliente);
    }
}
