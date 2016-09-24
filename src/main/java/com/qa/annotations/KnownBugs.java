package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Robbie on 9/23/2016.
 * To reference a known bug in jira. Default partial link provided here
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface KnownBugs {
    String[] bugs() default "http://jira.business.com/issue/browse/";
}
