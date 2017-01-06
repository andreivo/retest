package retest.annotation.params;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import retest.datatype.DataType;
import retest.datatype.IntegerDataType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface IntegerParam {

    String name() default "";

    Class<? extends DataType> randomizerClass() default IntegerDataType.class;
}
