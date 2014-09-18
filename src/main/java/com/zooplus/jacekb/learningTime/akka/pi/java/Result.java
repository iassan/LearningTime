package com.zooplus.jacekb.learningTime.akka.pi.java;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 14:51
 */
public class Result {
    private BigDecimal value;

    public Result(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
