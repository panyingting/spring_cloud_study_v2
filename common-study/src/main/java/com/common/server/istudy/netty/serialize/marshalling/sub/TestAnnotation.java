package com.common.server.istudy.netty.serialize.marshalling.sub;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

//public @interface TestAnnotation {
//}
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TestAnnotation {

    boolean requireAuth() default true;
    boolean requireValid() default false;
    boolean checkOnlineDevice() default true;
}