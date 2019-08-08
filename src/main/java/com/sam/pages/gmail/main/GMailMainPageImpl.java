package com.sam.pages.gmail.main;

import com.sam.pages.PageImpl;
import com.sam.pages.base.main.Main;
import com.sam.pages.gmail.main.compose_letter.GMailComposePage;
import com.sam.pages.gmail.main.compose_letter.GMailComposePageImpl;

public class GMailMainPageImpl extends PageImpl<Main> implements GMailMainPage {

    public GMailMainPageImpl() {
        super(new GMailMainImpl());
    }

    @Override
    public GMailComposePage compose(){
        content.clickCompose();
        GMailComposePage page = new GMailComposePageImpl();
        return page;
    }

}
