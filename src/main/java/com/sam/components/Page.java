package com.sam.components;

import java.awt.*;
import java.net.URL;

public interface Page<C extends Content> {

    C getContent();

    Boolean exists();

    void refresh();

    String getUrl();

    org.openqa.selenium.Dimension getSize();

    void setSize(int width, int height);

    default void setSize(Dimension dm) {
        setSize(dm.width, dm.height);
    }

    void close();

    void back();

    void forward();

    void navigateTo(String url);

    void navigateTo(URL url);

    void getURL();

    void quit();
}
