package ru.unlegit.reflector;

import lombok.NonNull;

import java.lang.reflect.Modifier;

public interface ClassAccessor<T> extends Declarable {

    @NonNull Class<T> getAccessedClass();

    @NonNull FieldAccessor getFieldAccess(@NonNull String fieldName) throws ReflectException;

    @NonNull MethodAccessor getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException;

    @NonNull ConstructorAccessor<T> getConstructorAccess(Class<?>... parameterTypes) throws ReflectException;
}