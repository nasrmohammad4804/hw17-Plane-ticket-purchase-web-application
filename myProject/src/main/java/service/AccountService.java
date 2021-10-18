package service;

import base.service.BaseService;
import domain.Account;

import java.util.Optional;

public interface AccountService  extends BaseService<Account,Long> {

    Optional<Account> findByUserName(String userName);

}

