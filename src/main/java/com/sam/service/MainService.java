package com.sam.service;

import com.sam.pages.cmain.CMainPage;

public abstract class MainService {

    public static CMainPage initFor(Class < ? extends CMainPage> impl){
        if (impl.isInterface()) {
            return null;
        }
       String name = impl.getCanonicalName();
       return create(name);
    }

    private static CMainPage create(String className){
       try{
           Class c = Class.forName(className);

           return (CMainPage)c.getDeclaredConstructor().newInstance();
       }
       catch(Exception e){System.out.println(e);}
       return null;
    }
}

