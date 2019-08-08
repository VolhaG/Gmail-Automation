package com.sam.pageservice;

import com.sam.pages.base.login.LoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;

import java.util.HashMap;
import java.util.Map;


public abstract class LoginService {

    private static Map<String, Class<? extends LoginPage>> loginImplementations = new HashMap<String, Class<? extends LoginPage>>() {{
        put("gmail", GMailLoginPageImpl.class);
    }};

    public static LoginPage initFor(String implementationName) {
        Class impl = loginImplementations.get(implementationName);

        if ((impl == null) | (impl.isInterface())) {
            return null;
        }
        String className = loginImplementations.get(implementationName).getCanonicalName();
        return createImplementationInstance(className);
    }

    private static LoginPage createImplementationInstance(String className) {
        try {
            Class c = Class.forName(className);

            return (LoginPage) c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

