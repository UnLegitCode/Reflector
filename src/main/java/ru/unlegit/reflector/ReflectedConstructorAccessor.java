package ru.unlegit.reflector;

import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectedConstructorAccessor<T> extends AbstractClassMemberAccessor<Constructor<T>> implements ConstructorAccessor<T> {

    public ReflectedConstructorAccessor(@NonNull Constructor<T> constructor) {
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