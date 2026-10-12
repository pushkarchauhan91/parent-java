package com.bharath.springdata.idgenerators;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Generates the entity id using {@link CustomRandomIDGenerator}.
 */
@IdGeneratorType(CustomRandomIDGenerator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CustomRandomId {
}
