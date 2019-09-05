package com.sam.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementVerification {
    int delayTime() default 4;
    String description() default "";
}
