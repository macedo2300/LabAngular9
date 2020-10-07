package com.br.crud.pibape.entity;


import com.br.crud.pibape.enums.PriorityEnum;
import com.br.crud.pibape.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;

    @DBRef(lazy = true)
    private User user;

    private LocalDateTime dateRegister;

    private String title;

    private Integer number;

    private StatusEnum status;

    private PriorityEnum priority;

    @DBRef(lazy = true)
    private User assingnedUser;

    private String description;

    private String image;

}
