package com.sam.pages.main.compose_letter;

import com.sam.pages.main.Main;

public interface ComposeLetter extends Main {
    void writeLetter(String startBody, String body, String endBody);
    void setSubject(String subject);
    void setRecipient(String subject);
    void sendLetter();
    void writeLetter(String body);
}
