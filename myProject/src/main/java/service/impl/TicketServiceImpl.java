package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.CompanyAccount;
import domain.Ticket;
import domain.TicketCompanyAccount;
import repository.impl.TicketRepositoryImpl;
import service.TicketService;
import service.util.ApplicationContext;
import service.util.SecurityContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketServiceImpl extends BaseServiceImpl<Ticket, Long, TicketRepositoryImpl> implements TicketService {



    public TicketServiceImpl(TicketRepositoryImpl repository) {
        super(repository);

    }

    @Override
    public void delete(Ticket element) {
        repository.delete(element);
    }

    @Override
    public void addNewTicket(Ticket ticket) {
        super.save(ticket);
    }

    @Override
    public Optional<Ticket> findCompanyAlreadyHaveSameTicket(String companyName, String origin, String destination, LocalDate flightDate) {
        return repository.findCompanyAlreadyHaveSameTicket(companyName,origin,destination,flightDate);
    }


}
