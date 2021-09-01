package com.example.dagger.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mac on 2020-11-23.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}


