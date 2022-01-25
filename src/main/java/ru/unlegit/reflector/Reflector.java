package ru.unlegit.reflector;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import ru.unlegit.reflector.internal.ReflectUtil;

@UtilityClass
public class Reflector {

    public static FieldAccessor getFieldAccess(@NonNull Class<?> declaringClass, @NonNull String fieldName) throws ReflectException {
        return new ReflectedFieldAccessor(ReflectUtil.getField(declaringClass, fieldName));
    }

    public static MethodAccessor getMethodAccess(@NonNull Class<?> declaringClass, @NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        return new ReflectedMethodAccessor(ReflectUtil.getMethod(declaringClass, methodName, argumentTypes));
    }

    public static <T> ConstructorAccessor<T> getConstructorAccess(@NonNull Class<T> declaringClass, Class<?>... parameterTypes) throws ReflectException {
        return new ReflectedConstructorAccessor<>(ReflectUtil.getConstructor(declaringClass, parameterTypes));
    }

    public static <T> ClassAccessor<T> getClassAccess(@NonNull AccessStrategy strategy, @NonNull Class<T> clazz) {
        return strategy == AccessStrategy.SIMPLE ? new SimpleClassAccessor<>(clazz) : new CachingClassAccessor<>(clazz);
    }

    public static <T> ObjectAccessor<T> getObjectAccess(@NonNull AccessStrategy strategy, @NonNull T object) {
        return strategy == AccessStrategy.SIMPLE ? new SimpleObjectAccessor<>(object) : new CachingObjectAccessor<>(object);
    }
}