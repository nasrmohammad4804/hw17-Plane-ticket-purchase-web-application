package repository;

import base.repository.BaseRepository;
import domain.TicketCompanyAccount;

import java.time.LocalDate;
import java.util.List;

public interface TicketCompanyAccountRepository extends BaseRepository<TicketCompanyAccount,Long> {
    List<TicketCompanyAccount> findAllByDestinationAndOrigin(String origin, String destination, LocalDate day, int passengerNumber);
    List<TicketCompanyAccount> findAllTicketBuyUser();

}
