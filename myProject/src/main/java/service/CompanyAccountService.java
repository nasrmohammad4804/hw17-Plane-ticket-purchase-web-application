package service;

import base.service.BaseService;
import domain.CompanyAccount;

public interface CompanyAccountService extends BaseService<CompanyAccount,Long> {

    CompanyAccount register(CompanyAccount companyAccount) throws Exception;

    Long countOfCompanyRegistered();
}
