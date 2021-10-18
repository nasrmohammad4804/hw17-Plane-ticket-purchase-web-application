package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.CompanyAccount;
import repository.impl.CompanyAccountRepositoryImpl;
import service.CompanyAccountService;

import java.time.LocalDateTime;

public class CompanyAccountServiceImpl extends BaseServiceImpl<CompanyAccount, Long, CompanyAccountRepositoryImpl>
        implements CompanyAccountService {



    public CompanyAccountServiceImpl(CompanyAccountRepositoryImpl repository) {
        super(repository);

    }


    @Override
    public void delete(CompanyAccount element) {
        repository.delete(element);
    }

    @Override
    public CompanyAccount register(CompanyAccount companyAccount)  {

      return super.save(companyAccount);
    }

    public void addDefaultCompany() {
        CompanyAccount companyAccount1 = new CompanyAccount("iranair1234", "1234iranair",
                LocalDateTime.now(), "iranair", "123456");

        CompanyAccount companyAccount2 = new CompanyAccount("taban1234", "1234taban",
                LocalDateTime.now(), "taban", "21xcvqwe");

        CompanyAccount companyAccount3 = new CompanyAccount("aseman1234", "1234aseman",
                LocalDateTime.now(), "aseman", "3333mn");

        super.save(companyAccount1);
        super.save(companyAccount2);
        super.save(companyAccount3);
    }


    @Override
    public Long countOfCompanyRegistered() {
        return repository.countOfCompanyRegistered();
    }
}
