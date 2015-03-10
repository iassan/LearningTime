package com.zooplus.jacekb.learningTime.functionalProgramming.optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class OptionalUsages {

	private static final Logger LOGGER = LoggerFactory.getLogger(OptionalUsages.class);

	@Test
	public void shouldShowHowMappingWorks() throws Exception {
		// given
		Optional<Long> input = Optional.of(1l);

		// when
		Optional<String> result = input.map(this::mapToString);

		// then
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo("1");
	}

	private String mapToString(Long input) {
		return input.toString();
	}

	@Test
	public void shouldExplainMapFunctionOnEmptyOptional() throws Exception {
		// given
		Optional<Long> present = Optional.of(1l);
		Optional<Long> absent = Optional.<Long>empty();

		// when
		Optional<Long> mappedPresent = present.map(this::mapFunction);
		Optional<Long> mappedAbsent = absent.map(this::mapFunction);

		// then
		assertThat(mappedPresent.isPresent()).isTrue();
		assertThat(mappedPresent.get()).isEqualTo(4);
		assertThat(mappedAbsent.isPresent()).isFalse();
	}

	private Long mapFunction(Long input) {
		LOGGER.info("mapFunction(" + input + ")");
		return input + 3;
	}

	@Test
	public void shouldExplainFilterFunction() throws Exception {
		// given
		Optional<Long> input = Optional.of(1l);

		// when
		Optional<Long> result = input.filter(data -> data > 3);

		// then
		assertThat(result.isPresent()).isFalse();
	}

	@Test
	public void shouldExplainOrElseBehaviour() throws Exception {
		// given
		Optional<Long> present = Optional.of(1l);
		Optional<Long> absent = Optional.<Long>empty();

		// when
		Long presentData = present.orElse(null);
		Long absentData = absent.orElse(null);

		// then
		assertThat(presentData).isNotNull().isEqualTo(1);
		assertThat(absentData).isNull();
	}

	@Test
	public void shouldExplainOrElseGetBehaviour() throws Exception {
		// given
		Optional<Long> absent = Optional.<Long>empty();

		// when
		Long result = absent.orElseGet(() -> 3l);

		// then
		assertThat(result).isNotNull().isEqualTo(3);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldExplainOrElseThrowBehaviour() throws Exception {
		// given
		Optional<Long> absent = Optional.<Long>empty();

		// when
		absent.orElseThrow(IllegalStateException::new);

		// then
		fail("Should have failed already");
	}
}
