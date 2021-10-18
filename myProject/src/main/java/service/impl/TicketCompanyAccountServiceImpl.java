package service.impl;

import base.service.impl.BaseServiceImpl;


import com.ghasemkiani.app.demo.PersianGregorianDateConverter;
import com.ghasemkiani.util.icu.PersianCalendar;
import com.ghasemkiani.util.icu.PersianDateFormat;
import com.ibm.icu.util.Calendar;
import domain.Ticket;
import domain.TicketCompanyAccount;
import domain.enumeration.SortingType;
import repository.impl.TicketCompanyAccountRepositoryImpl;
import service.TicketCompanyAccountService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketCompanyAccountServiceImpl extends BaseServiceImpl<TicketCompanyAccount, Long,
        TicketCompanyAccountRepositoryImpl> implements TicketCompanyAccountService {

    public TicketCompanyAccountServiceImpl(TicketCompanyAccountRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(TicketCompanyAccount element) {
        repository.delete(element);
    }

    @Override
    public List<TicketCompanyAccount> findAllByDestinationAndOrigin(String origin, String destination, LocalDate day, int passengerNumber) {
        List<TicketCompanyAccount> ticketCompanyAccounts =
                repository.findAllByDestinationAndOrigin(origin, destination, day, passengerNumber);


        LocalDate currentDate=LocalDate.now();
        LocalTime currentTime=LocalTime.now();

        return ticketCompanyAccounts.stream().filter(x -> (currentDate.isBefore(x.getTicket()
                .getTakeOfTime().toLocalDate()) || (currentDate.isEqual(x.getTicket().getTakeOfTime().toLocalDate()) &&
                x.getTicket().getTakeOfTime().toLocalTime().isAfter(currentTime)))).collect(Collectors.toList());
    }

    @Override
    public List<TicketCompanyAccount> findAllTicketBuyUser() {
        return repository.findAllTicketBuyUser();
    }

    @Override
    public List<TicketCompanyAccount> findAll() {
        List<TicketCompanyAccount> ticketList= repository.findAll();
        return   ticketList.stream().filter(x -> x.getTicket().getTakeOfTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

}
