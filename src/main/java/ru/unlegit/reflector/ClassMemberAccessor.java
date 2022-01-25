package ru.unlegit.reflector;

import lombok.NonNull;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Set;

public interface ClassMemberAccessor<T extends AccessibleObject & Member> extends Declarable {

    @NonNull Set<ClassMemberModifier> getModifiers();

    @NonNull ClassMemberModifier getAccessModifier();

    void openAccess();

    void closeAccess();

    boolean isAccessOpened();

    @NonNull Class<?> getDeclaringClass();

    @NonNull T getAccessedMember();
}