package com.pichincha.experiencia.model;

import lombok.Data;

@Data
public class Cliente {
	
	private Long id;
    private String name;
    private String email;  
    private String gender;
    private String status;

}
