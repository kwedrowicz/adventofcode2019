package me.wedrowicz.adventofcode.day04

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PasswordValidationTest {
    @ParameterizedTest
    @CsvSource(
        "111111,1",
        "223450,0",
        "123789,0"
    )
    fun `should validate passwords correctly`(password: Int, isValid: Int) {
        // when
        val res = PasswordValidation.isValid(password)
        // then
        Assertions.assertEquals(isValid.toBoolean(), res)
    }

    @ParameterizedTest
    @CsvSource(
        "112233,1",
        "123444,0",
        "111122,1"
    )
    fun `should validate passwords without matching groups`(password: Int, isValid: Int) {
        // when
        val res = PasswordValidation.isValidNotMatching(password)
        // then
        Assertions.assertEquals(isValid.toBoolean(), res)
    }

    private fun Int.toBoolean(): Boolean = this != 0
}
