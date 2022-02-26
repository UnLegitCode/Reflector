package ru.unlegit.reflector.clas;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.AbstractCachingAccessor;
import ru.unlegit.reflector.ReflectException;
import ru.unlegit.reflector.constructor.ConstructorAccessor;
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.method.MethodAccessor;

import java.lang.reflect.Modifier;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CachingClassAccessor<T> extends AbstractCachingAccessor<T> implements ClassAccessor<T> {

    @NonNull Class<T> accessedClass;

    @Override
    public @NonNull <R> FieldAccessor<R> getFieldAccess(@NonNull String fieldName) throws ReflectException {
        return getFieldAccess(accessedClass, fieldName);
    }

    @Override
    public @NonNull <R> MethodAccessor<R> getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        return getMethodAccess(accessedClass, methodName, argumentTypes);
    }

    @Override
    public @NonNull ConstructorAccessor<T> getConstructorAccess(Class<?>... parameterTypes) throws ReflectException {
        return getConstructorAccess(accessedClass, parameterTypes);
    }

    @Override
    public boolean isDeclared() {
        return !Modifier.isPublic(accessedClass.getModifiers());
    }

    private static <T> T throwIfNull(T object, ReflectException exception) throws ReflectException {
        if(object == null) {
            throw exception;
        } else {
            return object;
        }
    }
}