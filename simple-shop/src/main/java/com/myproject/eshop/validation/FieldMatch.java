package com.myproject.eshop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String message() default "Fields are not matching!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    @Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
    @Retention(RUNTIME)
    @interface List {
        FieldMatch[] value();
    }
}