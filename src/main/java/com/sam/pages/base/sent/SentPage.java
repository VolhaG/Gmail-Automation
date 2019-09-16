package com.sam.pages.base.sent;

import com.sam.entities.Letter;

public interface SentPage {

    boolean checkIfLetterSent(Letter letter);
    Letter getLastLetter();

}
