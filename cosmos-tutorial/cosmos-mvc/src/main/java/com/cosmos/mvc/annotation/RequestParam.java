package com.cosmos.mvc.annotation;

import java.lang.annotation.*;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 10:10
 * @Modified By：
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    String value() default "";
}
