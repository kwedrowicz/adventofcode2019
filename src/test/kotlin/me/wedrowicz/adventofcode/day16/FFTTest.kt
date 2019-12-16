package me.wedrowicz.adventofcode.day16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FFTTest {
    @Test
    fun `test 1`() {
        // given
        val inputSignal = "12345678"
        val fft = FFT(inputSignal)

        // when, then
        assertEquals("48226158", fft.next(1))
        assertEquals("34040438", fft.next(2))
        assertEquals("03415518", fft.next(3))
        assertEquals("01029498", fft.next(4))
    }
}
