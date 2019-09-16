package com.sam.pages.base.sent;

import com.sam.components.Content;
import com.sam.entities.Letter;

public interface Sent extends Content {

    String getLetterTopic(int rowIndex);

    String getLetterRecipient(int rowIndex);

    String getLetterBody(int rowIndex);

    Letter getLetter(int i);
}
