package com.pichincha.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.model.Cliente;

import reactor.core.publisher.Mono;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Long>{

	public Mono<Cliente> findByEmail(String email);

}
