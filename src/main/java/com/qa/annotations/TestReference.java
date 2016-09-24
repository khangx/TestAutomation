package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Robbie on 9/23/2016.
 * To tag and indicate where a test case is references. Default common partial is provided here
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface TestReference {
    String[] reference() default "http://testcasetool.com/case/";
}
