package com.video.store.domain.service.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.function.Consumer;

@UtilityClass
public final class ServiceUtils {

    /**
     * Method used to check if newValue is null and if so,
     * update old value to new
     *
     * @param newValue newValue
     * @param setter   setter
     * @param <T>      value's type
     */
    public  static <T> void updateIfNonNull(T newValue, Consumer<T> setter) {
        if (Objects.nonNull(newValue)) {
            setter.accept(newValue);
        }
    }
}
