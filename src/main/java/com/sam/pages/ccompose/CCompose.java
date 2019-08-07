package com.sam.pages.ccompose;

import com.sam.pages.cmain.CMain;
import com.sam.pages.gmail.main.Main;

public interface CCompose extends CMain {
    void writeLetter(String startBody, String body, String endBody);
    void setSubject(String subject);
    void setRecipient(String subject);
    void sendLetter();
    void writeLetter(String body);
    void close();
}
