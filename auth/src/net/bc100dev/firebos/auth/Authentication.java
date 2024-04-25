package net.bc100dev.firebos.auth;

import java.net.Authenticator;
import java.util.List;

public class Authentication {

    private final List<UserData> userList;

    protected Authentication(List<UserData> userList) {
        this.userList = userList;
    }

    public static Authentication getInstance() {
    }

}
