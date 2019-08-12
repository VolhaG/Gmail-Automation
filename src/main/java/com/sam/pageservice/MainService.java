package com.sam.pageservice;

import com.sam.pages.base.main.MainPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainService {

    private MainService() {
    }

    private static Map<Class<? extends MainPage>, Class<? extends MainPage>> mainImplementations = new HashMap<>();
    static {
        mainImplementations.put(GMailMainPage.class, GMailMainPageImpl.class);
    }

    public static <T extends MainPage> T initFor(Class<T> mainPageInterface) {
        if (!mainPageInterface.isInterface()) {
            throw new IllegalArgumentException("Argument must be an interface.");
        }
        Class<? extends MainPage> impl = mainImplementations.get(mainPageInterface);
        if (impl == null) {
            throw new NullPointerException("Implementation is absent for interface " + mainPageInterface.toString());
        }
        if (!mainPageInterface.isAssignableFrom(impl)) {
            throw new IllegalArgumentException("Implementation is not assigned with interface.");
        }
        String classImplementation = impl.getCanonicalName();
        return createImplementationInstance(classImplementation);
    }

    private static <T extends MainPage> T createImplementationInstance(String className) {
        T result = null;
        try {
            Class c = Class.forName(className);
            Constructor<T> constructor = c.getDeclaredConstructor();
            result = constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException ex) {
            ex.printStackTrace();
        }
        Objects.requireNonNull(result, "Main page is not initialized!");
        return result;
    }

}