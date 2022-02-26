package ru.unlegit.reflector.method;

import lombok.NonNull;
import ru.unlegit.reflector.AbstractClassMemberAccessor;
import ru.unlegit.reflector.ReflectException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectiveMethodAccessor<Type> extends AbstractClassMemberAccessor<Method> implements MethodAccessor<Type> {

    public ReflectiveMethodAccessor(@NonNull Method method) {
        super(method);
    }

    @Override
    public Type invoke(Object object, Object... arguments) throws ReflectException {
        openAccess();

        try {
            return (Type) accessedMember.invoke(object, arguments);
        } catch (IllegalAccessException exception) {
            throw new ReflectException(ReflectException.Reason.ILLEGAL_ACCESS);
        } catch (InvocationTargetException exception) {
            throw new ReflectException(ReflectException.Reason.INVOCATION_TARGET);
        } finally {
            closeAccess();
        }
    }
}