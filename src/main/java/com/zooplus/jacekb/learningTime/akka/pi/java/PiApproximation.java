package com.zooplus.jacekb.learningTime.akka.pi.java;

import scala.concurrent.duration.Duration;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 14:53
 */
public class PiApproximation {
    private BigDecimal pi;
    private Duration duration;

    public PiApproximation(BigDecimal pi, Duration duration) {
        this.pi = pi;
        this.duration = duration;
    }

    public BigDecimal getPi() {
        return pi;
    }

    public Duration getDuration() {
        return duration;
    }
}
