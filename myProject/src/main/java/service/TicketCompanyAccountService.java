package service;

import base.service.BaseService;
import domain.TicketCompanyAccount;

import java.time.LocalDate;
import java.util.List;

public interface TicketCompanyAccountService extends BaseService<TicketCompanyAccount,Long> {
    List<TicketCompanyAccount> findAllByDestinationAndOrigin(String origin, String destination, LocalDate day, int passengerNumber);
    List<TicketCompanyAccount> findAllTicketBuyUser();

}
