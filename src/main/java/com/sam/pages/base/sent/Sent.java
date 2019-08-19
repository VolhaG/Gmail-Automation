package com.sam.pages.base.sent;

import com.sam.components.Content;

public interface Sent extends Content {

    String getLetterTopic(int row);

    String getLetterRecipient(int row);

    String getLetterBody(int row);

}
