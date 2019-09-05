package com.sam.pages.base.sent;

import com.sam.components.Content;

public interface Sent extends Content {

    String getLetterTopic(int rowIndex);

    String getLetterRecipient(int rowIndex);

    String getLetterBody(int rowIndex);

}
