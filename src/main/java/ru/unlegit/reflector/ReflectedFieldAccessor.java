package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReflectedFieldAccessor extends AbstractClassMemberAccessor<Field> implements FieldAccessor {

    public ReflectedFieldAccessor(@NonNull Field field) {
        super(field);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getValue(Object object) throws ReflectException {
        openAccess();

        try {
            return (T) accessedMember.get(object);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } finally {
            closeAccess();
        }
    }

    @Override
    public void setValue(Object object, Object value) throws ReflectException {
        openAccess();

        try {
            accessedMember.set(object, value);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } finally {
            closeAccess();
        }
    }
}