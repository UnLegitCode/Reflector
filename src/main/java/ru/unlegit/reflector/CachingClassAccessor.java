package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Modifier;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CachingClassAccessor<T> extends AbstractCachingAccessor<T> implements ClassAccessor<T> {

    @NonNull Class<T> accessedClass;

    @Override
    public @NonNull FieldAccessor getFieldAccess(@NonNull String fieldName) throws ReflectException {
        return getFieldAccess(accessedClass, fieldName);
    }

    @Override
    public @NonNull MethodAccessor getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
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