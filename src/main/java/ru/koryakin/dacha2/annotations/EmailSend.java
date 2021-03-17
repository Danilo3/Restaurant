package ru.koryakin.dacha2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailSend {
    String to() default "contact@dacha-restaurant.ru";
    String subject() default "New";
}
