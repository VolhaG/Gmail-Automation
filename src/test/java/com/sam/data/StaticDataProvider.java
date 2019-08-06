package com.sam.data;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

    @DataProvider(name = "login")
    public static Object[][] createData() {
        return new Object[][] {
                        { "tt7381566@gmail.com", "test11111"}
        };
    }

}