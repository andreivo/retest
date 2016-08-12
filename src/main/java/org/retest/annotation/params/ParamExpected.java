package org.retest.annotation.params;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.retest.datatype.DataType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface ParamExpected {

    String name() default "";

    Class<? extends DataType> dataTypeClass();

}
