package com.sam.service;

import com.sam.pages.clogin.CLoginPage;

public abstract class LoginService {

    public static CLoginPage initFor(Class < ? extends CLoginPage> impl){
        if (impl.isInterface()) {
            return null;
        }
        String name = impl.getCanonicalName();
        return create(name);
    }

    private static CLoginPage create(String className){
        try{
            Class c = Class.forName(className);

            return (CLoginPage)c.getDeclaredConstructor().newInstance();
        }
        catch(Exception e){System.out.println(e);}
        return null;
    }
}

