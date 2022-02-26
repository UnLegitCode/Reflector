package ru.unlegit.reflector.field;

import ru.unlegit.reflector.ClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Field;

public interface FieldAccessor<Type> extends ClassMemberAccessor<Field> {

    Type getValue(Object object) throws ReflectException;

    void setValue(Object object, Type value) throws ReflectException;
}