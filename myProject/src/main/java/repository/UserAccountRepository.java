package repository;

import base.repository.BaseRepository;
import domain.UserAccount;

public interface UserAccountRepository extends BaseRepository<UserAccount,Long> {

    long countAllUserAdmin();
}
