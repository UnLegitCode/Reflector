package ru.unlegit.reflector.field;

import lombok.NonNull;

import java.lang.reflect.Field;

public class ReflectiveRawFieldAccessor extends ReflectiveFieldAccessor<Object> implements RawFieldAccessor {

    public ReflectiveRawFieldAccessor(@NonNull Field field) {
        super(field);
    }
}