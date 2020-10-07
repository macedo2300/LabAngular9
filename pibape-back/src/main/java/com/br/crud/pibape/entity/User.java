package com.br.crud.pibape.entity;


import com.br.crud.pibape.enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

	@Indexed(unique = true)
    @NotBlank(message = "Nome é requerido")
    private String nome;
	
	@Indexed(unique = true)
    @NotBlank(message = "Email é requerido")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Password é requerido")
    private String password;

    @NotBlank(message = "profile é requerido")
    private ProfileEnum profile;

    private Boolean favorite;

    private Binary image;

}
