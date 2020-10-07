package com.br.crud.pibape.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public enum ProfileEnum {

    ROLE_ADMIN,
    ROLE_CUSTOMER,
    ROLE_TECHNICIAN
}
