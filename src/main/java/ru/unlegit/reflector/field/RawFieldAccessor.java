package ru.unlegit.reflector.field;

import ru.unlegit.reflector.ReflectException;

public interface RawFieldAccessor extends FieldAccessor<Object> {

    default <T> T getTypedValue(Object object) throws ReflectException {
        return (T) getValue(object);
    }
}