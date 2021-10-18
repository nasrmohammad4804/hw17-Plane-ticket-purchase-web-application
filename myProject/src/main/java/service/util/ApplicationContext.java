package service.util;

import repository.impl.*;
import service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static  ApplicationContext applicationContext;


    private final EntityManager entityManager;
    private final TicketRepositoryImpl ticketRepository;
    private final AccountRepositoryImpl accountRepository;
    private final TicketServiceImpl ticketService;
    private final AccountServiceImpl accountService;
    private final CompanyAccountRepositoryImpl companyAccountRepository;
    private final CompanyAccountServiceImpl companyAccountService;
    private final UserAccountRepositoryImpl userAccountRepository;
    private final UserAccountServiceImpl userAccountService;
    private final TicketCompanyAccountRepositoryImpl ticketCompanyAccountRepository;
    private final TicketCompanyAccountServiceImpl ticketCompanyAccountService;

    private ApplicationContext(){

        entityManager=HibernateUtil.getEntityManagerFactory().createEntityManager();

        accountRepository=new AccountRepositoryImpl(entityManager);
        accountService=new AccountServiceImpl(accountRepository);
        ticketRepository=new TicketRepositoryImpl(entityManager);
        companyAccountRepository=new CompanyAccountRepositoryImpl(entityManager);
        userAccountRepository=new UserAccountRepositoryImpl(entityManager);

        userAccountService=new UserAccountServiceImpl(userAccountRepository);
        companyAccountService=new CompanyAccountServiceImpl(companyAccountRepository);

        ticketCompanyAccountRepository=new TicketCompanyAccountRepositoryImpl(entityManager);
        ticketCompanyAccountService=new TicketCompanyAccountServiceImpl(ticketCompanyAccountRepository);
        ticketService=new TicketServiceImpl(ticketRepository);
    }
    public  static synchronized ApplicationContext getApplicationContext(){
        if (applicationContext==null)
            applicationContext=new ApplicationContext();

        return applicationContext;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public TicketRepositoryImpl getTicketRepository() {
        return ticketRepository;
    }

    public AccountRepositoryImpl getAccountRepository() {
        return accountRepository;
    }

    public TicketServiceImpl getTicketService() {
        return ticketService;
    }

    public AccountServiceImpl getAccountService() {
        return accountService;
    }

    public CompanyAccountRepositoryImpl getCompanyAccountRepository() {
        return companyAccountRepository;
    }

    public CompanyAccountServiceImpl getCompanyAccountService() {
        return companyAccountService;
    }

    public UserAccountRepositoryImpl getUserAccountRepository() {
        return userAccountRepository;
    }

    public UserAccountServiceImpl getUserAccountService() {
        return userAccountService;
    }



    public TicketCompanyAccountRepositoryImpl getTicketCompanyAccountRepository() {
        return ticketCompanyAccountRepository;
    }

    public TicketCompanyAccountServiceImpl getTicketCompanyAccountService() {
        return ticketCompanyAccountService;
    }

}
