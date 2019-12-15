package me.wedrowicz.adventofcode.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class IntCodeProgramExtendedTest {


    @Test
    fun `if test`() {
        // given
        val programCode = listOf(3,9,8,9,10,9,4,9,99,-1,8)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(8)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(0)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }

    @Test
    fun `if test 2`() {
        // given
        val programCode = listOf(3,9,7,9,10,9,4,9,99,-1,8)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(7)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(8)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }

    @Test
    fun `if test 3`() {
        // given
        val programCode = listOf(3,3,1108,-1,8,3,4,3,99)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(8)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(0)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }

    @Test
    fun `if test 4`() {
        // given
        val programCode = listOf(3,3,1107,-1,8,3,4,3,99)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(7)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(8)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }


    @Test
    fun `jump test`() {
        // given
        val programCode = listOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(1)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(0)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }

    @Test
    fun `jump test 2`() {
        // given
        val programCode = listOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1)
        // when
        val res1 = IntCodeProgramExtended(programCode).runWithIfAndJumps(1)
        val res0 = IntCodeProgramExtended(programCode).runWithIfAndJumps(0)
        // then
        Assertions.assertEquals(1, res1)
        Assertions.assertEquals(0, res0)
    }
}
