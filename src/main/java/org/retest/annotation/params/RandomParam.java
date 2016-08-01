package org.retest.annotation.params;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.retest.randomizer.Randomizer;
import org.retest.randomizer.RandomSeedRandomizer;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface RandomParam {

    String name() default "";

    Class<? extends Randomizer> randomizerClass() default RandomSeedRandomizer.class;
}
