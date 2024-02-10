package com.labuhn.minesweeper.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


class FlagStatusTest {

    @Test
    public void enumsShouldHaveStrictOrder(){
        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(FlagStatus.values()).sorted().forEach(status ->
                Assertions.assertThat(status.getOrder()).as("%s shoudld have order %d",
                        status.toString(),i.get()).isEqualTo(i.getAndIncrement())
        );
    }

    @ParameterizedTest
    @MethodSource("nextMappings")
    public void shouldGetNextStatus(FlagStatus currentStatus, FlagStatus nextStatus){
        Assertions.assertThat(FlagStatus.next(currentStatus)).isEqualTo(nextStatus);
    }

    public static Stream<Arguments> nextMappings(){
        return Stream.of(Arguments.of(FlagStatus.NOT_MARKED,FlagStatus.MARKED_AS_MINE),
                Arguments.of(FlagStatus.MARKED_AS_MINE,FlagStatus.MARKED_AS_UNKNOWN),
                Arguments.of(FlagStatus.MARKED_AS_UNKNOWN,FlagStatus.NOT_MARKED)
                );
    }
}