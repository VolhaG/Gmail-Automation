package com.sam.pages.main;

import com.sam.PageImpl;

public class MainPage extends PageImpl<Main> {

    public MainPage() {
        super(new MainImpl());
    }

    public String getTitle() {
        return null;
    }
}
