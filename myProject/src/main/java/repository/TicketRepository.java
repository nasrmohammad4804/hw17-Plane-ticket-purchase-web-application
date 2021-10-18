package repository;

import base.repository.BaseRepository;
import domain.Ticket;

import java.time.LocalDate;
import java.util.Optional;

public interface TicketRepository extends BaseRepository<Ticket,Long> {

   Optional<Ticket> findCompanyAlreadyHaveSameTicket(String companyName, String origin, String destination, LocalDate flightDate);
}
