package me.wedrowicz.adventofcode.day08

class SpaceImageFormat(private val content: String, width: Int, height: Int) {
    private val layerSize = width * height
    private val layers = content.length / layerSize

    fun findLayerWithFewestZeros(): Int {
        var bestLayer = -1
        var fewestZeros = Int.MAX_VALUE

        for(i in 0 until layers) {
            val layerI = content.substring(i*layerSize, i*layerSize+layerSize)
            val zeros = layerI.count { it == '0' }

            if(zeros < fewestZeros) {
                bestLayer = i
                fewestZeros = zeros
            }
        }

        val bestLayerContent = content.substring(bestLayer * layerSize, bestLayer * layerSize + layerSize)
        val ones = bestLayerContent.count { it == '1' }
        val twos = bestLayerContent.count { it == '2' }

        return ones * twos
    }

    fun mergeLayers(): List<Int> {
        val mergedLayer = MutableList(layerSize) { 2 }
        for(i in 0 until layerSize) {
            var position = i
            while (position < content.length) {
                if(content[position] != '2') {
                    mergedLayer[i] = content[position] - '0'
                    break
                }
                position += layerSize
            }
        }

        return mergedLayer
    }
}
