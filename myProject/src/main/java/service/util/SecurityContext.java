package service.util;

import domain.Account;

public class SecurityContext {

    private static Account myAccount;

    public static void login(Account account) {
        myAccount = account;
    }

    public static Account getCurrentAccount() {
        return myAccount;
    }

    public static void logout() {
        myAccount = null;
    }
}
