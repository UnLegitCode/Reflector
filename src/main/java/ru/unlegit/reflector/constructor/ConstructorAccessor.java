package ru.unlegit.reflector.constructor;

import lombok.NonNull;
import ru.unlegit.reflector.ClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Constructor;

public interface ConstructorAccessor<T> extends ClassMemberAccessor<Constructor<T>> {

    @NonNull T newInstance(Object... parameters) throws ReflectException;
}