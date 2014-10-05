package com.zooplus.jacekb.learningTime.akka.pi.java;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 14:53
 */
public class PiApproximation implements Serializable {

	private BigDecimal pi;

	private Long duration;

	public PiApproximation(BigDecimal pi, Long duration) {
		this.pi = pi;
		this.duration = duration;
	}

	public BigDecimal getPi() {
		return pi;
	}

	public Long getDuration() {
		return duration;
	}
}
