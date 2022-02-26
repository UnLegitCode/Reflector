package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractClassMemberAccessor<T extends AccessibleObject & Member> implements ClassMemberAccessor<T> {

    Set<ClassMemberModifier> modifiers;
    ClassMemberModifier accessModifier;
    T accessedMember;

    public AbstractClassMemberAccessor(@NonNull T accessedMember) {
        this.accessedMember = accessedMember;
        Set<ClassMemberModifier> tempModifiers = EnumSet.noneOf(ClassMemberModifier.class);

        for (ClassMemberModifier modifier : ClassMemberModifier.values()) {
            if(modifier.isModified(accessedMember)) tempModifiers.add(modifier);
        }

        if(tempModifiers.contains(ClassMemberModifier.PUBLIC)) {
            accessModifier = ClassMemberModifier.PUBLIC;
        } else if(tempModifiers.contains(ClassMemberModifier.PROTECTED)) {
            accessModifier = ClassMemberModifier.PROTECTED;
        } else if(tempModifiers.contains(ClassMemberModifier.PRIVATE)) {
            accessModifier = ClassMemberModifier.PRIVATE;
        } else {
            accessModifier = ClassMemberModifier.DEFAULT;
        }

        modifiers = Collections.unmodifiableSet(tempModifiers);
    }

    @Override
    public void openAccess() {
        if(!isAccessOpened()) accessedMember.setAccessible(true);
    }

    @Override
    public void closeAccess() {
        if(isAccessOpened()) accessedMember.setAccessible(false);
    }

    @Override
    public boolean isAccessOpened() {
        return accessedMember.isAccessible();
    }

    @Override
    public @NonNull Class<?> getDeclaringClass() {
        return accessedMember.getDeclaringClass();
    }

    @Override
    public boolean isDeclared() {
        return accessModifier != ClassMemberModifier.PUBLIC;
    }
}