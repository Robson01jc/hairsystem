package com.robsonrocha.util;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * The JPA DAO Qualifier
 * 
 * @author robson
 * @since 19 December 2016
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD, TYPE, PARAMETER })
public @interface JPADAO {

}