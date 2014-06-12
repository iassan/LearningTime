package com.zooplus.jacekb.learningTime.validation;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 12.06.14
 * Time: 12:11
 */
@Service
@Validated
@Lazy
@Retention(RetentionPolicy.RUNTIME)
public @interface ZooplusService {
}
