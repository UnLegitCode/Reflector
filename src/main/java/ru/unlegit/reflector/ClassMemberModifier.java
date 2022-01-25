package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.function.IntPredicate;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ClassMemberModifier {

    PUBLIC(Modifier::isPublic, ClassMember.values()),
    PROTECTED(Modifier::isProtected, ClassMember.values()),
    DEFAULT(modifiers -> !Modifier.isPublic(modifiers) && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers), ClassMember.values()),
    PRIVATE(Modifier::isPrivate, ClassMember.values()),
    NATIVE(Modifier::isNative, new ClassMember[] {ClassMember.METHOD}),
    FINAL(Modifier::isFinal, new ClassMember[] {ClassMember.FIELD, ClassMember.METHOD}),
    STATIC(Modifier::isStatic, new ClassMember[] {ClassMember.FIELD, ClassMember.METHOD}),
    SYNCHRONIZED(Modifier::isSynchronized, new ClassMember[] {ClassMember.METHOD}),
    VOLATILE(Modifier::isVolatile, new ClassMember[] {ClassMember.FIELD}),
    TRANSIENT(Modifier::isTransient, new ClassMember[] {ClassMember.FIELD});

    IntPredicate modifiedTest;
    @Getter ClassMember[] availableElements;

    public boolean isModified(@NonNull Member member) {
        return modifiedTest.test(member.getModifiers());
    }
}