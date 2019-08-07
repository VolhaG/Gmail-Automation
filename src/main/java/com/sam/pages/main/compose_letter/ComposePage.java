package com.sam.pages.main.compose_letter;

import com.sam.PageImpl;
import com.sam.components.Content;
import com.sam.components.Page;
import com.sam.pages.main.MainPageImpl;

public interface ComposePage extends Page<Content>{

     void writeLetter(String recipient, String subject, String letter);

     void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     MainPageImpl sendLetter();

     MainPageImpl closePage();

}
