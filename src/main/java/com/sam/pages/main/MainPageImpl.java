package com.sam.pages.main;

import com.sam.PageImpl;
import com.sam.pages.main.compose_letter.ComposePageImpl;

public class MainPageImpl extends PageImpl<Main> implements MainPage {

    public MainPageImpl() {
        super(new MainImpl());
    }

    @Override
    public ComposePageImpl compose(){
        content.clickCompose();
        return new ComposePageImpl();
    }

}
