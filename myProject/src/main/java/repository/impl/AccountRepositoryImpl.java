package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import repository.AccountRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(Account element) {

        element.setIsDeleted(true);
        super.update(element);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Optional<Account> findByUserName(String userName) {

        Optional<Account> optional = Optional.empty();

        try {

            Account account = entityManager.createQuery("select a from Account as a where a.userName=:user_name",
                    Account.class).setParameter("user_name", userName).getSingleResult();

            return Optional.of(account);

        } catch (Exception e) {
            return optional;
        }
    }
}
