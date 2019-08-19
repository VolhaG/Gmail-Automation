package com.sam.pageservice;

import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.compose.GMailComposePageImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ComposeService {

    private ComposeService() {
    }

    private static Map<Class<? extends ComposePage>, Class<? extends ComposePage>> composeImplementations = new HashMap<>();

    static {
        composeImplementations.put(GMailComposePage.class, GMailComposePageImpl.class);
    }

    public static <T extends ComposePage> T initFor(Class<T> composePageInterface) {
        if (!composePageInterface.isInterface()) {
            throw new IllegalArgumentException("Argument must be an interface.");
        }
        Class<? extends ComposePage> impl = composeImplementations.get(composePageInterface);
        if (impl == null) {
            throw new NullPointerException("Implementation is absent for interface " + composePageInterface.toString());
        }
        if (!composePageInterface.isAssignableFrom(impl)) {
            throw new IllegalArgumentException("Implementation is not assigned with interface.");
        }
        String classImplementation = impl.getCanonicalName();
        T result = null;
        try {
            Class c = Class.forName(classImplementation);
            Constructor<T> constructor = c.getDeclaredConstructor();
            result = constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException ex) {
            ex.printStackTrace();
        }
        Objects.requireNonNull(result, "Main page is not initialized!");
        return result;
    }

}