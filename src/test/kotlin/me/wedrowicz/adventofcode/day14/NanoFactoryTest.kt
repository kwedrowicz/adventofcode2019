package me.wedrowicz.adventofcode.day14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NanoFactoryTest {
    @Test
    fun `test 1`() {
        // given
        val reactions = parse("14_1_1.in")
        // when
        val res = NanoFactory(reactions).calculateFuelUnitCost()
        // then
        assertEquals(31, res)
    }

    @Test
    fun `test 2`() {
        // given
        val reactions = parse("14_1_2.in")
        // when
        val res = NanoFactory(reactions).calculateFuelUnitCost()
        // then
        assertEquals(165, res)
    }

    @Test
    fun `task 2 test 1`() {
        // given
        val reactions = parse("14_1_3.in")
        // when
        val res = NanoFactory(reactions).calculateFuelForOneUnits(1000000000000)
        // then
        assertEquals(82892753, res)
    }
}
