package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.internal.BiContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class AbstractCachingAccessor<T> {

    Map<String, FieldAccessor> fieldAccessorMap = new HashMap<>();
    Map<BiContainer<String, Class<?>[]>, MethodAccessor> methodAccessorMap = new HashMap<>();
    Map<Class<?>[], ConstructorAccessor<T>> constructorAccessorMap = new HashMap<>();

    protected FieldAccessor getFieldAccess(@NonNull Class<T> accessedClass, @NonNull String fieldName) throws ReflectException {
        AtomicReference<ReflectException> exceptionReference = new AtomicReference<>();

        FieldAccessor fieldAccessor = fieldAccessorMap.computeIfAbsent(fieldName, s -> {
            try {
                return Reflector.getFieldAccess(accessedClass, fieldName);
            } catch (ReflectException exception) {
                exceptionReference.set(exception);

                return null;
            }
        });

        return throwIfNull(fieldAccessor, exceptionReference.get());
    }

    protected MethodAccessor getMethodAccess(@NonNull Class<T> accessedClass, @NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        AtomicReference<ReflectException> exceptionReference = new AtomicReference<>();

        MethodAccessor methodAccessor = methodAccessorMap.computeIfAbsent(new BiContainer<>(methodName, argumentTypes), keyContainer -> {
            try {
                return Reflector.getMethodAccess(accessedClass, methodName, argumentTypes);
            } catch (ReflectException exception) {
                exceptionReference.set(exception);

                return null;
            }
        });

        return throwIfNull(methodAccessor, exceptionReference.get());
    }

    protected @NonNull ConstructorAccessor<T> getConstructorAccess(@NonNull Class<T> accessedClass, Class<?>... parameterTypes) throws ReflectException {
        AtomicReference<ReflectException> exceptionReference = new AtomicReference<>();

        ConstructorAccessor<T> constructorAccessor = constructorAccessorMap.computeIfAbsent(parameterTypes, types -> {
            try {
                return Reflector.getConstructorAccess(accessedClass, parameterTypes);
            } catch (ReflectException exception) {
                exceptionReference.set(exception);

                return null;
            }
        });

        return throwIfNull(constructorAccessor, exceptionReference.get());
    }

    private static <T> T throwIfNull(T object, ReflectException exception) throws ReflectException {
        if(object == null) {
            throw exception;
        } else {
            return object;
        }
    }
}