package ru.unlegit.reflector.clas;

import lombok.NonNull;
import ru.unlegit.reflector.Declarable;
import ru.unlegit.reflector.ReflectException;
import ru.unlegit.reflector.constructor.ConstructorAccessor;
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.method.MethodAccessor;

public interface ClassAccessor<T> extends Declarable {

    @NonNull Class<T> getAccessedClass();

    @NonNull <R> FieldAccessor<R> getFieldAccess(@NonNull String fieldName) throws ReflectException;

    @NonNull <R> MethodAccessor <R> getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException;

    @NonNull ConstructorAccessor<T> getConstructorAccess(Class<?>... parameterTypes) throws ReflectException;
}