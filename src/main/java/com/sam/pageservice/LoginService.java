package com.sam.pageservice;

import com.sam.pages.base.login.LoginPage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class LoginService {

    private static Map<Class<? extends LoginPage>, Class<? extends LoginPage>> loginImplementations =
            new HashMap<>();
           static {
                loginImplementations.put(GMailLoginPage.class, GMailLoginPageImpl.class);
            }

    public static <T extends LoginPage> T  initFor(Class<T> loginPageInterface) throws Exception {
        if (!loginPageInterface.isInterface()) {
            throw new IllegalArgumentException("Argument must be an interface.");
        }
        Class<? extends LoginPage> impl = loginImplementations.get(loginPageInterface);
        if (impl == null) {
            throw new ClassNotFoundException("Implementation is absent for interface " + loginPageInterface.toString());
        }
//        Class<? extends LoginPage>[] interfaces = impl.getInterfaces();
//        boolean interfaceIsAssigned = false;
//        for (Class<?> anInterface : interfaces) {
//            if (anInterface.equals(loginPageInterface)) {
//                interfaceIsAssigned = true;
//                break;
//            }
//        }
//        if (!interfaceIsAssigned) {
//            throw new ClassNotFoundException("Implementation is not assigned with interface.");
//        }
        if (!loginPageInterface.isAssignableFrom(impl)) {
            throw new IllegalArgumentException("Implementation is not assigned with interface.");
        }
        String classImplementation = impl.getCanonicalName();
        return createImplementationInstance(classImplementation);
    }

    private static <T extends LoginPage> T createImplementationInstance(String className) {
        Class c = null;
        T result = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Constructor<T> constructor = c.getDeclaredConstructor();
            result = constructor.newInstance();

        } catch (InstantiationException|InvocationTargetException| IllegalAccessException| NoSuchMethodException | NullPointerException ex){
            ex.printStackTrace();
        }

        return result;

    }
}

