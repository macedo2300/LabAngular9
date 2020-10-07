package com.br.crud.pibape.enums;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public enum StatusEnum {
    NEW,
    ASSIGNED,
    RESOLVED,
    APPROVED,
    DISAPPROVED,
    CLOSED;

    public static StatusEnum getStatus(String status){

        switch (status){
            case "ASSIGNED" : return ASSIGNED;
            case "RESOLVED" : return RESOLVED;
            case "APPROVED" : return APPROVED;
            case "DISAPPROVED" : return DISAPPROVED;
            case "CLOSED" : return CLOSED;
            default: return NEW;
        }
    }
}
