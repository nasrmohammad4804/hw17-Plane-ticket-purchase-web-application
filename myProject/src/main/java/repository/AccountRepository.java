package repository;

import base.repository.BaseRepository;
import domain.Account;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account,Long> {

    Optional<Account> findByUserName(String userName);

}
