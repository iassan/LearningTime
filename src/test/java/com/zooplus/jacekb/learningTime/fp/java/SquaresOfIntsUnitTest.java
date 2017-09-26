package com.zooplus.jacekb.learningTime.fp.java;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

public class SquaresOfIntsUnitTest {

    @Test
    public void generateSuares() {
        // given
        SquaresOfInts generator = new NaiveSquaresOfInts();

        // when
        List<Integer> result = generator.squaresOfInts(5);

        // then
        assertThat(result).isEqualTo(Lists.newArrayList(1, 4, 9, 16, 25));
    }
}
