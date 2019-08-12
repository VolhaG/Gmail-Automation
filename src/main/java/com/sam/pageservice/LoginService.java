package com.sam.pageservice;

import com.sam.pages.base.login.LoginPage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginService {

    private LoginService() {
    }

    private static Map<Class<? extends LoginPage>, Class<? extends LoginPage>> loginImplementations =
            new HashMap<>();

    static {
        loginImplementations.put(GMailLoginPage.class, GMailLoginPageImpl.class);
    }

    public static <T extends LoginPage> T initFor(Class<T> loginPageInterface) {
        if (!loginPageInterface.isInterface()) {
            throw new IllegalArgumentException("Argument must be an interface.");
        }
        Class<? extends LoginPage> impl = loginImplementations.get(loginPageInterface);
        if (impl == null) {
            throw new NullPointerException("Implementation is absent for interface " + loginPageInterface.toString());
        }
        if (!loginPageInterface.isAssignableFrom(impl)) {
            throw new IllegalArgumentException("Implementation is not assigned with interface.");
        }
        String classImplementation = impl.getCanonicalName();
        return createImplementationInstance(classImplementation);
    }

    private static <T extends LoginPage> T createImplementationInstance(String className) {
        T result = null;
        try {
            Class c = Class.forName(className);
            Constructor<T> constructor = c.getDeclaredConstructor();
            result = constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException ex) {
            ex.printStackTrace();
        }
        Objects.requireNonNull(result, "Login page is not initialized!");
        return result;
    }

}

