package ru.unlegit.reflector.clas;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.ReflectException;
import ru.unlegit.reflector.constructor.ConstructorAccessor;
import ru.unlegit.reflector.constructor.ReflectiveConstructorAccessor;
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.field.ReflectiveFieldAccessor;
import ru.unlegit.reflector.internal.ReflectUtil;
import ru.unlegit.reflector.method.MethodAccessor;
import ru.unlegit.reflector.method.ReflectiveMethodAccessor;

import java.lang.reflect.Modifier;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleClassAccessor<T> implements ClassAccessor<T> {

    @NonNull Class<T> accessedClass;

    public SimpleClassAccessor(Class<T> clazz) {
        accessedClass = clazz;
    }

    @Override
    public @NonNull <R> FieldAccessor<R> getFieldAccess(@NonNull String fieldName) throws ReflectException {
        return new ReflectiveFieldAccessor<>(ReflectUtil.getField(accessedClass, fieldName));
    }

    @Override
    public @NonNull <R> MethodAccessor<R> getMethodAccess(@NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        return new ReflectiveMethodAccessor<>(ReflectUtil.getMethod(accessedClass, methodName, argumentTypes));
    }

    @Override
    public @NonNull ConstructorAccessor<T> getConstructorAccess(Class<?>... parameterTypes) throws ReflectException {
        return new ReflectiveConstructorAccessor<>(ReflectUtil.getConstructor(accessedClass, parameterTypes));
    }

    @Override
    public boolean isDeclared() {
        return !Modifier.isPublic(accessedClass.getModifiers());
    }
}