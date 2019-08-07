package com.sam.pages.gmail.main;

import com.sam.PageImpl;
import com.sam.pages.cmain.CMain;
import com.sam.pages.gmail.main.compose_letter.ComposePage;
import com.sam.pages.gmail.main.compose_letter.ComposePageImpl;

public class MainPageImpl extends PageImpl<CMain> implements MainPage {

    public MainPageImpl() {
        super(new MainImpl());
    }

    @Override
    public ComposePage compose(){
        content.clickCompose();
        ComposePage page = new ComposePageImpl();
        return page;
    }

}
