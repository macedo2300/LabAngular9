package com.br.crud.pibape.repositoty;

import com.br.crud.pibape.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
