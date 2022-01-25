package ru.unlegit.reflector;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ReflectException extends Exception {

    Reason reason;

    public ReflectException(@NonNull Reason reason) {
        super(reason.name());

        this.reason = reason;
    }

    public enum Reason {

        ILLEGAL_ACCESS,
        NO_SUCH_FIELD,
        NO_SUCH_METHOD,
        NO_SUCH_CONSTRUCTOR,
        INVOCATION_TARGET,
        INSTANTIATION
    }
}