package com.piyus.sb_customAnnotationAndProxies.annotation;

// no need to perform any logic - annotations actually do nothing
// , it just act as a marker annotation

// meta data annotation - it tell where annotation can be applied
// it takes enum ElementType.<EnumName>

import java.lang.annotation.*;

//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface TrackExecutionTime {
//    // empty
//}


// need to perform any logic
// , it's know as Configured annotation

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackExecutionTime {

    long warnAfter() default 2000;

    String operation() default "";
}
