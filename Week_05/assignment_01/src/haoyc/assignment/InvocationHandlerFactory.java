package haoyc.assignment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

public class InvocationHandlerFactory {

    public static InvocationHandler build(Object target, Class clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        assert clazz.isAssignableFrom(InvocationHandler.class);
        Constructor constructor = clazz.getConstructor(Object.class);
        return (InvocationHandler) constructor.newInstance(target);
    }
}
