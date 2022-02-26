package ru.unlegit.reflector.method;

import ru.unlegit.reflector.ReflectException;

public interface RawMethodAccessor extends MethodAccessor<Object> {

    default <R> R invokeTyped(Object object, Object... arguments) throws ReflectException {
        return (R) invoke(object, arguments);
    }
}