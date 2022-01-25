package ru.unlegit.reflector;

import java.lang.reflect.Field;

public interface FieldAccessor extends ClassMemberAccessor<Field> {

    <T> T getValue(Object object) throws ReflectException;

    void setValue(Object object, Object value) throws ReflectException;
}