package com.zooplus.jacekb.learningTime.akka.pi.java;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 18.09.14
 * Time: 14:31
 */
public class PiCalculator {

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    public BigDecimal calculatePiFor(int start, int nrOfElements) {
        BigDecimal acc = BigDecimal.ZERO;
        for (int i = start; i < start + nrOfElements; i++) {
            acc = acc.add(new BigDecimal(4).multiply(new BigDecimal(1 - i % 2 * 2)).divide(new BigDecimal(2 * i + 1), MATH_CONTEXT));
        }
        return acc;
    }
}
