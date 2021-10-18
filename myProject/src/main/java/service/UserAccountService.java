package service;

import base.service.BaseService;
import domain.UserAccount;

public interface UserAccountService extends BaseService<UserAccount,Long> {

    UserAccount register(UserAccount userAccount) ;

    long countAllUserAdmin();

}
