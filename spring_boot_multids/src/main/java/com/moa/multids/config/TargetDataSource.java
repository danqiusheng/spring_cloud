package com.moa.multids.config;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/10/20.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}


