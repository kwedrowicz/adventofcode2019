package me.wedrowicz.adventofcode.day08

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SpaceImageFormatTest {
    @Test
    fun `should merge layers`() {
        // given
        val content = "0222112222120000"
        val width = 2
        val height = 2
        // when
        val mergedLayer = SpaceImageFormat(content, width, height).mergeLayers()
        // then
        val expectedLayer = "0110"
        assertEquals(expectedLayer, mergedLayer.joinToString(""))
    }
}
