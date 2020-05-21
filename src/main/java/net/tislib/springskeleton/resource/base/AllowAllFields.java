package net.tislib.springskeleton.resource.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// if resource class is annotated with AllowAllFields annotation we bypass field check
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AllowAllFields {
}
