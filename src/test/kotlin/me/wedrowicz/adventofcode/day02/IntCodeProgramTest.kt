package me.wedrowicz.adventofcode.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class IntCodeProgramTest {
    @ParameterizedTest
    @MethodSource("programProvider")
    fun `should return program result`(program: List<Int>, result: List<Int>) {
        Assertions.assertEquals(result, IntCodeProgram(program).run())
    }

    companion object {
        @JvmStatic
        fun programProvider() = listOf(
            Arguments.of(listOf(1, 0, 0, 0, 99), listOf(2, 0, 0, 0, 99)),
            Arguments.of(listOf(2, 3, 0, 3, 99), listOf(2, 3, 0, 6, 99)),
            Arguments.of(listOf(2, 4, 4, 5, 99, 0), listOf(2, 4, 4, 5, 99, 9801)),
            Arguments.of(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        )
    }
}
