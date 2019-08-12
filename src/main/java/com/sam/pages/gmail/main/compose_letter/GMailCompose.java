package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.gmail.main.GMailMain;

public interface GMailCompose extends GMailMain {

    void writeLetter(String startBody, String body, String endBody);

    void setSubject(String subject);

    void setRecipient(String subject);

    void sendLetter();

    void writeLetter(String body);

    void close();

}