package ru.unlegit.reflector;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import ru.unlegit.reflector.clas.CachingClassAccessor;
import ru.unlegit.reflector.clas.ClassAccessor;
import ru.unlegit.reflector.clas.SimpleClassAccessor;
import ru.unlegit.reflector.constructor.ConstructorAccessor;
import ru.unlegit.reflector.constructor.ReflectiveConstructorAccessor;
import ru.unlegit.reflector.field.FieldAccessor;
import ru.unlegit.reflector.field.ReflectiveFieldAccessor;
import ru.unlegit.reflector.internal.ReflectUtil;
import ru.unlegit.reflector.method.MethodAccessor;
import ru.unlegit.reflector.method.ReflectiveMethodAccessor;
import ru.unlegit.reflector.object.CachingObjectAccessor;
import ru.unlegit.reflector.object.ObjectAccessor;
import ru.unlegit.reflector.object.SimpleObjectAccessor;

@UtilityClass
public class Reflector {

    public static <Type> FieldAccessor<Type> getFieldAccess(@NonNull Class<?> declaringClass, @NonNull String fieldName) throws ReflectException {
        return new ReflectiveFieldAccessor<Type>(ReflectUtil.getField(declaringClass, fieldName));
    }

    public static<T> MethodAccessor<T> getMethodAccess(@NonNull Class<?> declaringClass, @NonNull String methodName, Class<?>... argumentTypes) throws ReflectException {
        return new ReflectiveMethodAccessor<>(ReflectUtil.getMethod(declaringClass, methodName, argumentTypes));
    }

    public static <T> ConstructorAccessor<T> getConstructorAccess(@NonNull Class<T> declaringClass, Class<?>... parameterTypes) throws ReflectException {
        return new ReflectiveConstructorAccessor<>(ReflectUtil.getConstructor(declaringClass, parameterTypes));
    }

    public static <T> ClassAccessor<T> getClassAccess(@NonNull AccessStrategy strategy, @NonNull Class<T> clazz) {
        return strategy == AccessStrategy.SIMPLE ? new SimpleClassAccessor<>(clazz) : new CachingClassAccessor<>(clazz);
    }

    public static <T> ObjectAccessor<T> getObjectAccess(@NonNull AccessStrategy strategy, @NonNull T object) {
        return strategy == AccessStrategy.SIMPLE ? new SimpleObjectAccessor<>(object) : new CachingObjectAccessor<>(object);
    }
}