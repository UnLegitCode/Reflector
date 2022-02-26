package ru.unlegit.reflector.method;

import lombok.NonNull;
import ru.unlegit.reflector.ClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Method;

public interface MethodAccessor<Type> extends ClassMemberAccessor<Method> {

    Type invoke(Object object, Object... arguments) throws ReflectException;

    default Type invoke(Object object, @NonNull Iterable<Object> arguments) throws ReflectException {
        int size = 0;

        for (Object argument : arguments) size++;

        if(size == 0) return invoke(object);

        Object[] argumentArray = new Object[size];

        int index = 0;

        for (Object argument : arguments) {
            argumentArray[index] = argument;

            index++;
        }

        Object argument = null;

        for(int i = 0; i < size; i++, argument = arguments.iterator().next()) {
            argumentArray[i] = argument;
        }

        return invoke(object, argumentArray);
    }
}