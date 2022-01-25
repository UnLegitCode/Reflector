package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ClassMember {

    FIELD(Field.class, new ClassMemberModifier[] {}),
    METHOD(Method.class, new ClassMemberModifier[] {}),
    CONSTRUCTOR(Constructor.class, new ClassMemberModifier[] {});

    Class<? extends Member> memberClass;
    ClassMemberModifier[] availableModifiers;
}