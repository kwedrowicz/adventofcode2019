package me.wedrowicz.adventofcode.day16

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.sign

class FFT(private val input: String) {
    fun next(phases: Int): String {
        var signal = "" + input
        val sequence = listOf(0, 1, 0, -1)
        for (x in 1..phases) {
            // println("PHASE: $x")
            var newSignal = ""

            for (i in signal.indices) {
                // println("INDEX: $i")
                var digit = BigInteger.ZERO
                // println(sequence)
                val currentSequence: MutableList<Int> = sequence.flatMap { el -> List(i + 1) { el } }.toMutableList()
                currentSequence.removeAt(0)
                currentSequence.add(0)

                // println(currentSequence)

                for (j in signal.indices) {
                    // println("$digit, ${signal[j]}, ${currentSequence[j % currentSequence.size]}")
                    digit = (digit + BigInteger.valueOf(((signal[j] - '0') * currentSequence[j % currentSequence.size]).toLong()))
                }
                newSignal += digit.abs().mod(BigInteger("10")).toString()
            }
            signal = newSignal
        }

        return signal
    }

    fun nextWithOffset(phases: Int, offset: Int): String {
        val signal = input.repeat(10000).map { (it - '0').toLong() }.toMutableList()

        for(x in 1..phases) {
            var partialSum = signal.subList(offset, signal.size).sum()
            for(i in offset until signal.size) {
                val t = partialSum
                partialSum -= signal[i].toInt()
                signal[i] = abs(t).rem(10)
            }
        }

        return signal.subList(offset, offset+8).joinToString(separator = "")
    }
}
