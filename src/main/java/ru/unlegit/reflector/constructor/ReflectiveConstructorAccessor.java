package ru.unlegit.reflector.constructor;

import lombok.NonNull;
import ru.unlegit.reflector.AbstractClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectiveConstructorAccessor<T> extends AbstractClassMemberAccessor<Constructor<T>> implements ConstructorAccessor<T> {

    public ReflectiveConstructorAccessor(@NonNull Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public @NonNull T newInstance(Object... parameters) throws ReflectException {
        openAccess();

        try {
            return accessedMember.newInstance(parameters);
        } catch (InstantiationException exception) {
            throw new ReflectException(ReflectException.Reason.INSTANTIATION);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } catch (InvocationTargetException exception) {
            throw new ReflectException(ReflectException.Reason.INVOCATION_TARGET);
        } finally {
            closeAccess();
        }
    }
}