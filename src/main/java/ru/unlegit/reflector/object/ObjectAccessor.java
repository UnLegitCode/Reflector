package ru.unlegit.reflector.object;

import lombok.NonNull;
import ru.unlegit.reflector.ReflectException;

public interface ObjectAccessor<T> {

    <R> R getFieldValue(@NonNull String fieldName) throws ReflectException;

    void setFieldValue(@NonNull String fieldName, Object value) throws ReflectException;

    <R> R invokeMethod(String methodName, Object... arguments) throws ReflectException;

    @NonNull T getAccessedObject();
}