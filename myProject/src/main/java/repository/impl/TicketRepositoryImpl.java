package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Ticket;
import repository.TicketRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket, Long> implements TicketRepository {

    public TicketRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(Ticket element) {

        element.setIsDeleted(true);
        super.update(element);
    }

    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

    @Override
    public Optional<Ticket> findCompanyAlreadyHaveSameTicket(String companyName, String origin, String destination, LocalDate flightDate) {

        Optional<Ticket> optional = Optional.empty();
        try {
            Ticket ticket = entityManager.createQuery("select t from Ticket as t" +
                    " left  join t.ticketCompanyAccounts as tca" +
                    " where tca.companyAccount.name=:comName and t.originCity=:origin and " +
                    "t.destinationCity=:destination and substring(t.takeOfTime,1,10)=:flightDate ", Ticket.class)
                    .getSingleResult();

            return Optional.of(ticket);
        } catch (Exception e) {
            return optional;
        }

    }


}

