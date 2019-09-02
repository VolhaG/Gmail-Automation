package com.sam.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PageVerification {
    String locator();

    String info() default "";
    int delayTime() default 1;
    ByType byType() default ByType.CSS;
}
