package com.br.crud.pibape.dto;

import com.br.crud.pibape.entity.User;
import com.br.crud.pibape.enums.ProfileEnum;
import lombok.*;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private String id;

    private String nome;

    private String email;

    private String password;

    private String profile;

    private boolean favorite;

    private String image;


    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = user.getProfile()==null? null: user.getProfile().toString();
        this.favorite = user.getFavorite()== null? false : user.getFavorite();
        this.image = user.getImage()==null ? null : Base64.getEncoder().encodeToString(user.getImage().getData());
    }

    public UserResponseDTO(String id, String nome, String email,String password,String profile,boolean favorite,String image) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.favorite = favorite;
        this.image = image;
    }


}