package com.sam.pageservice;


import com.sam.pages.base.main.MainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

import java.util.HashMap;
import java.util.Map;

public abstract class MainService {

    private static Map<String, Class<? extends MainPage>> mainImplementations = new HashMap<String, Class<? extends MainPage>>() {{
        put("gmail", GMailMainPageImpl.class);
    }};

    public static MainPage initFor(String implementationName) {
        Class impl = mainImplementations.get(implementationName);

        if ((impl == null) || (impl.isInterface())) {
            return null;
        }
        String className = mainImplementations.get(implementationName).getCanonicalName();
        return createImplementationInstance(className);
    }

    private static MainPage createImplementationInstance(String className) {
        try {
            Class c = Class.forName(className);

            return (MainPage) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

