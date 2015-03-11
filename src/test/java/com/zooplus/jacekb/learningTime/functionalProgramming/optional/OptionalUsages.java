package com.zooplus.jacekb.learningTime.functionalProgramming.optional;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;

public class OptionalUsages {

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
		Optional<Long> absent = Optional.empty();

		// when
		Optional<Long> mappedPresent = present.map(this::mapFunction);
		Optional<Long> mappedAbsent = absent.map(this::mapFunction);

		// then
		assertThat(mappedPresent.isPresent()).isTrue();
		assertThat(mappedPresent.get()).isEqualTo(4);
		assertThat(mappedAbsent.isPresent()).isFalse();
	}

	private Long mapFunction(Long input) {
		return input + 3;
	}

    @Test
    public void shouldExplainFlatMapFunction() throws Exception {
        // given
        Optional<String> valid = Optional.of("123");
        Optional<String> invalid = Optional.of("abc");

        // when
        Optional<Long> validNumber = valid.flatMap(this::mapToLong);
        Optional<Long> invalidNumber = invalid.flatMap(this::mapToLong);

        // then
        assertThat(validNumber.isPresent()).isTrue();
        assertThat(validNumber.get()).isEqualTo(123);
        assertThat(invalidNumber.isPresent()).isFalse();
    }

    private Optional<Long> mapToLong(String input) {
        try {
            return Optional.of(Long.parseLong(input));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
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
		Optional<Long> absent = Optional.empty();

		// when
		Long presentData = present.orElse(null);
		Long absentData = absent.orElse(3l);

		// then
		assertThat(presentData).isNotNull().isEqualTo(1);
		assertThat(absentData).isNotNull().isEqualTo(3);
	}

	@Test
	public void shouldExplainOrElseGetBehaviour() throws Exception {
		// given
		Optional<Long> absent = Optional.empty();

		// when
		Long result = absent.orElseGet(() -> 3l);

		// then
		assertThat(result).isNotNull().isEqualTo(3);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldExplainOrElseThrowBehaviour() throws Exception {
		// given
		Optional<Long> absent = Optional.empty();

		// when
		absent.orElseThrow(IllegalStateException::new);

		// then
		fail("Should have failed already");
	}

	@Test
	public void shouldExplainIfPresentFunction() throws Exception {
		// given
		Optional<Long> present = Optional.of(1l);
		Optional<Long> absent = Optional.empty();
		Consumer<Long> consumer = Mockito.mock(Consumer.class);

		// when
		absent.ifPresent(consumer);

		// then
		then(consumer).should(never()).accept(anyLong());

		// when
		present.ifPresent(consumer);

		// then
		then(consumer).should().accept(1l);
	}
}
