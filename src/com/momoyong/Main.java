package com.momoyong;

import com.login.AccountManager;

public class Main {

    public static void main(String[] args) {
	// write your code here

      AccountManager manager=new AccountManager();

      String gustRet = manager.GustLogin();

      String pwdRet = manager.pwdLogin(gustRet);

      String check=manager.check(pwdRet);

    }



}
