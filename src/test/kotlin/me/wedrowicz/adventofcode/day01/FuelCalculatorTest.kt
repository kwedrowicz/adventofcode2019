package me.wedrowicz.adventofcode.day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class FuelCalculatorTest {

    private val calculator = FuelCalculator()

    @ParameterizedTest
    @CsvSource(
        "12, 2",
        "14, 2",
        "1969, 654",
        "100756, 33583"
    )
    fun `should calculate fuel for single module`(moduleMass: Int, expectedFuel: Int) {
        Assertions.assertEquals(expectedFuel, calculator.calculateForModule(moduleMass))
    }

    @Test
    fun `should calculate fuel for all modules`() {
        // given
        val moduleMasses = listOf(12, 14, 1969, 100756)
        // when
        val res = calculator.calculateForAllModules(moduleMasses)
        // then
        val expectedFuel = listOf(2, 2, 654, 33583).sum()
        Assertions.assertEquals(expectedFuel, res)
    }

    @ParameterizedTest
    @CsvSource(
        "14, 2",
        "1969, 966",
        "100756, 50346"
    )
    fun `should calculate fuel for single module including fuel mass itself`(moduleMass: Int, expectedFuel: Int) {
        Assertions.assertEquals(expectedFuel, calculator.calculateForModuleWithFuelMass(moduleMass))
    }

    @Test
    fun `should calculate fuel for all modules including fuel mass itself`() {
        // given
        val moduleMasses = listOf(14, 1969, 100756)
        // when
        val res = calculator.calculateForAllModulesWithFuelMass(moduleMasses)
        // then
        val expectedFuel = listOf(2, 966, 50346).sum()
        Assertions.assertEquals(expectedFuel, res)
    }
}
