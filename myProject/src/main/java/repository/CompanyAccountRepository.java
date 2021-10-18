package repository;

import base.repository.BaseRepository;
import domain.CompanyAccount;

public interface CompanyAccountRepository extends BaseRepository<CompanyAccount,Long> {

    Long countOfCompanyRegistered();
}
