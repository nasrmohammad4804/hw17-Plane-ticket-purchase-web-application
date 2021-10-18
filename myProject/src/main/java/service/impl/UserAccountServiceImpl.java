package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.UserAccount;
import repository.impl.UserAccountRepositoryImpl;
import service.UserAccountService;
import service.util.SecurityContext;

public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount, Long, UserAccountRepositoryImpl>
        implements UserAccountService {


    public UserAccountServiceImpl(UserAccountRepositoryImpl repository) {
        super(repository);

    }

    @Override
    public void delete(UserAccount element) {
        repository.delete(element);
    }

    @Override
    public UserAccount register(UserAccount userAccount) {

       return super.save(userAccount);
    }

    @Override
    public long countAllUserAdmin() {
        return repository.countAllUserAdmin();
    }

    public void addDefaultUserAdmin() {

        UserAccount userAccount1 = new UserAccount("javad", "zare", "1283214907", "men");
        userAccount1.setUserName("javad1234");
        userAccount1.setPassword("1234javad");
        userAccount1.setAdmin(true);

        UserAccount userAccount2 = new UserAccount("mahmood", "karimi", "1278213409", "men");
        userAccount2.setUserName("mahmood1234");
        userAccount2.setPassword("1234mahmood");
        userAccount2.setAdmin(true);


        UserAccount userAccount3 = new UserAccount("ali", "karimi", "1293128765", "men");
        userAccount3.setUserName("ali1234");
        userAccount3.setPassword("1234ali");
        userAccount3.setAdmin(true);

        super.save(userAccount1);
        super.save(userAccount2);
        super.save(userAccount3);

    }

}
