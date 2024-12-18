package com.pichincha.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "cliente")
@Data
@AllArgsConstructor
public class Cliente {

	@Id
    private Long id;

    private String name;
    
    private String email;
    
    private String gender;
    
    private String status;
    	
}
