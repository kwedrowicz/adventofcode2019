package me.wedrowicz.adventofcode.day09

import me.wedrowicz.adventofcode.common.IntCodeVM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.pow

internal class IntCodeVMTest {
    @Test
    fun `test 1`() {
        // given
        val program = listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99).map { it.toBigInteger() }
        // when
        val output = IntCodeVM(program).execute()
        // then
        assertEquals(program, output)
    }

    @Test
    fun `test 2`() {
        // given
        val program = listOf(1102,34915192,34915192,7,4,7,99,0).map { it.toBigInteger() }
        // when
        val output = IntCodeVM(program).execute()
        // then
        assertTrue(output[0] >= 10.toDouble().pow(15).toBigDecimal().toBigInteger())
        assertTrue(output[0] < 10.toDouble().pow(16).toBigDecimal().toBigInteger())
    }

    @Test
    fun `test 3`() {
        // given
        val program = listOf(104,1125899906842624,99).map { it.toBigInteger() }
        // when
        val output = IntCodeVM(program).execute()
        // then
        assertEquals(program[1], output[0])
    }
}
