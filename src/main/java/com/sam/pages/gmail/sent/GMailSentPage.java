package com.sam.pages.gmail.sent;

import com.sam.pages.base.sent.SentPage;
import com.sam.entities.Letter;

public interface GMailSentPage extends SentPage {
    public Letter getLastLetter();
}
