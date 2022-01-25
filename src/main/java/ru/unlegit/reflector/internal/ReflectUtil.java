package ru.unlegit.reflector.internal;

import lombok.experimental.UtilityClass;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@UtilityClass
public class ReflectUtil {

    public static Field getField(Class<?> declaringClass, String fieldName) throws ReflectException {
        Field field;

        try {
            field = declaringClass.getField(fieldName);
        } catch (NoSuchFieldException exception) {
            try {
                field = declaringClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException exception1) {
                throw new ReflectException(ReflectException.Reason.NO_SUCH_FIELD);
            }
        }

        return field;
    }

    public static Method getMethod(Class<?> declaringClass, String methodName, Class<?>... argumentTypes) throws ReflectException {
        Method method;

        try {
            method = declaringClass.getMethod(methodName, argumentTypes);
        } catch (NoSuchMethodException exception) {
            try {
                method = declaringClass.getDeclaredMethod(methodName, argumentTypes);
            } catch (NoSuchMethodException exception1) {
                throw new ReflectException(ReflectException.Reason.NO_SUCH_METHOD);
            }
        }

        return method;
    }

    public static <T >Constructor<T> getConstructor(Class<T> declaringClass, Class<?>... parameterTypes) throws ReflectException {
        Constructor<T> constructor;

        try {
            constructor = declaringClass.getConstructor(parameterTypes);
        } catch (NoSuchMethodException exception) {
            try {
                constructor = declaringClass.getDeclaredConstructor(parameterTypes);
            } catch (NoSuchMethodException exception1) {
                throw new ReflectException(ReflectException.Reason.NO_SUCH_CONSTRUCTOR);
            }
        }

        return constructor;
    }
}