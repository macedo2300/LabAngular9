package com.br.crud.pibape.controller;


import com.br.crud.pibape.dto.UserResponseDTO;
import com.br.crud.pibape.entity.User;
import com.br.crud.pibape.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    private String id;
    private MultipartFile arquivo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public User saveUser(@RequestBody User user) {
        return this.userService.createOrUpdate(user);
    }

    @GetMapping
    public Page<UserResponseDTO> listUsers(
            @RequestParam( value = "page",defaultValue = "0") Integer page,
            @RequestParam( value = "sizePage", defaultValue = "10") Integer sizePage
    ){
        PageRequest pageRequest = PageRequest.of(page,sizePage);

        Page<User> lista = this.userService.findAll(pageRequest);
        
        return lista.map(UserResponseDTO::new );
    }

    @PatchMapping("{id}/favorite")
    public void favorite(@PathVariable String id) {

        Optional<User> user = this.userService.findById(id);

        user.ifPresent( c->{

            boolean favorite = c.getFavorite() == Boolean.TRUE;
            c.setFavorite(!favorite);
            this.userService.createOrUpdate(c);
        });

    }


    @PutMapping("{id}/photo")
    public byte[] addPhoto(@PathVariable String id, @RequestParam("photo") MultipartFile arquivo) {
        this.id = id;
        this.arquivo = arquivo;
        Optional<User> userOptional = this.userService.findById(id);

        return userOptional.map(user -> {
            try {
                byte[] bytes = new byte[(int)arquivo.getSize()];
                user.setImage(
                        new Binary(BsonBinarySubType.BINARY,arquivo.getBytes())
                );
                this.userService.createOrUpdate(user);
                return arquivo.getBytes();
            } catch (IOException e) {
                return null;
            }
        }).orElse(null) ;
    }


    private UserResponseDTO createUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .nome(user.getNome())
                .email(user.getEmail())
                .password(user.getPassword())
                .favorite(user.getFavorite()== null? false : user.getFavorite())
                .profile(user.getProfile() == null ? null : user.getProfile().toString())
                .image(user.getImage()==null ? null : Base64.getEncoder().encodeToString(user.getImage().getData()))
                .build();
    }
}
