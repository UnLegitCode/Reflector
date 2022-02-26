package ru.unlegit.reflector.object;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.AbstractCachingAccessor;
import ru.unlegit.reflector.ReflectException;
import ru.unlegit.reflector.internal.ArrayUtil;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CachingObjectAccessor<T> extends AbstractCachingAccessor<T> implements ObjectAccessor<T> {

    @NonNull T accessedObject;

    @Override
    public <R> R getFieldValue(@NonNull String fieldName) throws ReflectException {
        return this.<R>getFieldAccess(getAccessedObjectClass(), fieldName).getValue(accessedObject);
    }

    @Override
    public void setFieldValue(@NonNull String fieldName, Object value) throws ReflectException {
        getFieldAccess(getAccessedObjectClass(), fieldName).setValue(accessedObject, value);
    }

    @Override
    public <R> R invokeMethod(String methodName, Object... arguments) throws ReflectException {
        return this.<R>getMethodAccess(getAccessedObjectClass(), methodName, ArrayUtil.mapClasses(arguments)).invoke(accessedObject, arguments);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getAccessedObjectClass() {
        return (Class<T>) accessedObject.getClass();
    }
}