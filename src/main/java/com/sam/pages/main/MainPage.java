package com.sam.pages.main;

import com.sam.PageImpl;
import com.sam.pages.main.compose_letter.ComposePage;

public class MainPage extends PageImpl<Main> {

    public MainPage() {
        super(new MainImpl());
    }

    public ComposePage compose(){
        content.clickCompose();
        return new ComposePage();
    }

}
