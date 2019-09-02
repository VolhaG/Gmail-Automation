package com.sam.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PageVerification {

    String info() default "";
    int delayTime() default 1;
    String locator() default "";
    ByType byType() default ByType.CSS;
}
