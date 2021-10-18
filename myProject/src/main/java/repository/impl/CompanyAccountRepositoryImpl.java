package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.CompanyAccount;
import repository.CompanyAccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CompanyAccountRepositoryImpl extends BaseRepositoryImpl<CompanyAccount, Long> implements CompanyAccountRepository {

    public CompanyAccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(CompanyAccount element) {

        element.setIsDeleted(true);
        super.update(element);
    }

    @Override
    public Class<CompanyAccount> getEntityClass() {
        return CompanyAccount.class;
    }

    @Override
    public Long countOfCompanyRegistered() {

        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<CompanyAccount> root = criteriaQuery.from(CompanyAccount.class);

        criteriaQuery.select(builder.count(root.get("id")));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            return 0L;
        }

    }
}
