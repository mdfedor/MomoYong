package com.momoyong;

import com.login.AccountManager;

public class Main {

    public static void main(String[] args) {
	// write your code here

      AccountManager manager=new AccountManager();

      String gustRet = manager.GustLogin();

      String pwdRet = manager.pwdLogin(gustRet);


      String sendtext="you have zero connection.";

      String onlytext="And before they know it, their actual friends start to fade away.";

      //String checkRet=manager.textCheck(pwdRet,onlytext);

      //String send=manager.textSend(onlytext);

       String checkPhotoret=manager.photoCheck(pwdRet,sendtext);

       String sendPhotoret=manager.photoSend("C:\\Users\\JOY\\Pictures\\dog.jpg",sendtext);

    }

}
