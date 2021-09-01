package com.example.dagger.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by mac on 2020-11-23.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface UserQualifier {
    String value() default "";
}

