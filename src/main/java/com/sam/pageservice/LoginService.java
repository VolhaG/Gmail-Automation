package com.sam.pageservice;

import com.sam.pages.base.login.LoginPage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;
import com.sam.utils.IllegalAssignableClassesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private static Logger log = LogManager.getLogger(LoginService.class);

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
            throw new IllegalAssignableClassesException("Implementation is not assigned with interface.");
        }
        String classImplementation = impl.getCanonicalName();
        try {
            Class c = Class.forName(classImplementation);
            Constructor<T> constructor = c.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException ex) {
            ex.printStackTrace();
        }
        log.info("Login page is null!");
        return null;
    }

}

