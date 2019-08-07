package com.sam.pages.ccompose;

import com.sam.components.Page;
import com.sam.pages.cmain.CMainPage;
import com.sam.pages.gmail.main.MainPageImpl;

public interface CComposePage extends Page<CCompose>  {

    void writeLetter(String recipient, String subject, String letter);

    void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

    CMainPage sendLetter();

    CMainPage closePage();
}
