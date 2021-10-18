package service;

import base.service.BaseService;
import domain.Ticket;

import java.time.LocalDate;
import java.util.Optional;

public interface TicketService extends BaseService<Ticket,Long> {
    void addNewTicket(Ticket ticket);
    Optional<Ticket> findCompanyAlreadyHaveSameTicket(String companyName, String origin, String destination, LocalDate flightDate);

}
