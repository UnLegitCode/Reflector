package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.internal.ReflectUtil;

import java.lang.reflect.Modifier;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleClassAccessor<T> implements ClassAccessor<T> {

    @NonNull Class<T> accessedClass;

    SimpleClassAccessor(Class<T> clazz) {
        accessedClass = clazz;
    }

    @Override
    public @NonNull FieldAccessor getFieldAccess(@NonNull String fieldName) throws ReflectException {
        return new ReflectedFieldAccessor(ReflectUtil.getField(accessedClass, fieldName));
    }

    @Override
    public @NonNull MethodAccessor getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        return new ReflectedMethodAccessor(ReflectUtil.getMethod(accessedClass, methodName, argumentTypes));
    }

    @Override
    public @NonNull ConstructorAccessor<T> getConstructorAccess(Class<?>... parameterTypes) throws ReflectException {
        return new ReflectedConstructorAccessor<>(ReflectUtil.getConstructor(accessedClass, parameterTypes));
    }

    @Override
    public boolean isDeclared() {
        return !Modifier.isPublic(accessedClass.getModifiers());
    }
}