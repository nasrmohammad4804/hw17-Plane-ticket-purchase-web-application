package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.TicketCompanyAccount;
import repository.TicketCompanyAccountRepository;
import service.util.SecurityContext;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class TicketCompanyAccountRepositoryImpl extends BaseRepositoryImpl<TicketCompanyAccount, Long>
        implements TicketCompanyAccountRepository {

    public TicketCompanyAccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(TicketCompanyAccount element) {
        element.setIsDeleted(true);
        super.update(element);

    }

    @Override
    public Class<TicketCompanyAccount> getEntityClass() {
        return TicketCompanyAccount.class;
    }

    @Override
    public List<TicketCompanyAccount> findAllByDestinationAndOrigin(String origin, String destination, LocalDate day,int passengerNumber) {

        return entityManager.createQuery("select tca from TicketCompanyAccount as tca where " +
                        "tca.ticket.originCity=:origin and tca.ticket.destinationCity=:dest" +
                        " and substring(tca.ticket.takeOfTime,1,10) =:myTime and tca.ticket.capacity>=:number  ",
                TicketCompanyAccount.class)
                .setParameter("origin", origin).setParameter("dest", destination)
                .setParameter("myTime", day.toString()).setParameter("number",passengerNumber).getResultList();
    }

    @Override
    public List<TicketCompanyAccount> findAllTicketBuyUser() {

        return entityManager.createQuery("select tca from UserAccount as u left join" +
                " u.ticketCompanyAccounts as tca " +
                " where u.id=:id", TicketCompanyAccount.class).
                setParameter("id", SecurityContext.getCurrentAccount().getId()).getResultList();
    }


}
