package ru.unlegit.reflector.internal;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtil {

    public static Class<?>[] mapClasses(@NonNull Object[] array) {
        Class<?>[] classMap = new Class[array.length];

        for (int i = 0; i < array.length; i++) {
            classMap[i] = array[i].getClass();
        }

        return classMap;
    }
}