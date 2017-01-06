package retest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import retest.datafile.TestDataFiles;
import retest.datafile.formats.CSVTestDataFiles;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaveSuccessTestDataFiles {

    String filePath() default "";

    Class<? extends TestDataFiles> formatClass() default CSVTestDataFiles.class;
}
