package ru.unlegit.reflector.field;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.unlegit.reflector.AbstractClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Field;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReflectiveFieldAccessor<Type> extends AbstractClassMemberAccessor<Field> implements FieldAccessor<Type> {

    public ReflectiveFieldAccessor(@NonNull Field field) {
        super(field);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Type getValue(Object object) throws ReflectException {
        openAccess();

        try {
            return (Type) accessedMember.get(object);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } finally {
            closeAccess();
        }
    }

    @Override
    public void setValue(Object object, Type value) throws ReflectException {
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