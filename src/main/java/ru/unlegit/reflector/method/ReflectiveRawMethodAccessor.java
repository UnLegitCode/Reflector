package ru.unlegit.reflector.method;

import lombok.NonNull;

import java.lang.reflect.Method;

public class ReflectiveRawMethodAccessor extends ReflectiveMethodAccessor<Object> implements RawMethodAccessor {

    public ReflectiveRawMethodAccessor(@NonNull Method method) {
        super(method);
    }
}