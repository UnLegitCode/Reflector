package ru.unlegit.reflector;

import lombok.NonNull;

import java.lang.reflect.Constructor;

public interface ConstructorAccessor<T> extends ClassMemberAccessor<Constructor<T>> {

    @NonNull T newInstance(Object... parameters) throws ReflectException;
}