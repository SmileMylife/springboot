package com.example.springboot.annotations;

import java.lang.annotation.*;

/**
 * Created by ZhangPei on 2018/12/1.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InputObject {

}
