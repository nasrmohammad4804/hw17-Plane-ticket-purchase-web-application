package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Account;
import repository.impl.AccountRepositoryImpl;
import service.AccountService;

import java.util.Optional;

public class AccountServiceImpl extends BaseServiceImpl<Account,Long, AccountRepositoryImpl> implements AccountService {

    public AccountServiceImpl(AccountRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(Account element) {
        repository.delete(element);
    }


    @Override
    public Optional<Account> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}
