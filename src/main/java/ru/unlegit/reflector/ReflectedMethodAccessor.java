package ru.unlegit.reflector;

import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectedMethodAccessor extends AbstractClassMemberAccessor<Method> implements MethodAccessor {

    public ReflectedMethodAccessor(@NonNull Method method) {
        super(method);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T invoke(Object object, Object... arguments) throws ReflectException {
        openAccess();

        try {
            return (T) accessedMember.invoke(object, arguments);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } catch (InvocationTargetException exception) {
            throw new ReflectException(ReflectException.Reason.INVOCATION_TARGET);
        } finally {
            closeAccess();
        }
    }
}