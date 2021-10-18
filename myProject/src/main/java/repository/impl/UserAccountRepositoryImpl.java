package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.UserAccount;
import repository.UserAccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserAccountRepositoryImpl extends BaseRepositoryImpl<UserAccount, Long> implements UserAccountRepository {

    public UserAccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(UserAccount element) {
        element.setIsDeleted(true);
        super.update(element);
    }

    @Override
    public Class<UserAccount> getEntityClass() {
        return UserAccount.class;
    }


    @Override
    public long countAllUserAdmin() {

        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<UserAccount> root = criteriaQuery.from(UserAccount.class);
       criteriaQuery.select(builder.count(root.get("id")));
        criteriaQuery.where(builder.equal(root.get("isAdmin"), true));


        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();

        } catch (Exception e) {
            return 0;
        }

    }
}
