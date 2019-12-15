package me.wedrowicz.adventofcode.day14

import kotlin.math.ceil

class NanoFactory(private val reactions: List<ChemicalReaction>) {

    private val reactionsMap = mutableMapOf<String, ChemicalReaction>()

    init {
        for(reaction in reactions) {
            reactionsMap[reaction.output.name] = reaction
        }
    }

    fun calculateFuelUnitCost(): Long {
        return calculateCostInOre(FUEL, 1L, mutableMapOf<String, Long>())
    }

    fun calculateFuelForOneUnits(ore: Long): Long {
        var left = 0L
        var right = ore
        var res = 0L

        while(left <= right) {
            val mid = (left+right) / 2L
            if(calculateCostInOre(FUEL, mid, mutableMapOf<String, Long>()) <= ore) {
                res = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return res
    }

    private fun calculateCostInOre(name: String, quantity: Long, surplus: MutableMap<String, Long>): Long {
        val ingredientSurplus = surplus.getOrPut(name, { 0L })

        if(name == ORE) {
            return quantity
        } else if(quantity <= ingredientSurplus) {
            surplus[name] = ingredientSurplus.minus(quantity)
            return 0
        }
        val (inputs, output) = reactionsMap[name]!!
        val left = quantity - ingredientSurplus
        surplus[name] = 0
        val copies = ceil(left/output.quantity.toDouble()).toInt()

        val ore =  inputs.fold(0L) { acc, el -> acc + calculateCostInOre(el.name, el.quantity * copies, surplus)}

        surplus[name] = output.quantity * copies - left

        return ore
    }

    companion object {
        const val ORE = "ORE"
        const val FUEL = "FUEL"
    }
}

data class ChemicalReaction(
    val inputs: List<Ingredient>,
    val output: Ingredient
)

data class Ingredient(
    val name: String,
    val quantity: Long
)
