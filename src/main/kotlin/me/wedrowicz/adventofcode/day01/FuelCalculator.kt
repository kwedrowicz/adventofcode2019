package me.wedrowicz.adventofcode.day01

class FuelCalculator {
    fun calculateForModule(mass: Int): Int {
        return mass / 3 - 2
    }

    fun calculateForModuleWithFuelMass(mass: Int): Int {
        return calculateFuelRecursively(mass)
    }

    fun calculateForAllModules(masses: List<Int>): Int {
        return masses.fold(0) {sum, element -> sum + calculateForModule(element)}
    }

    fun calculateForAllModulesWithFuelMass(masses: List<Int>): Int {
        return masses.fold(0) {sum, element -> sum + calculateFuelRecursively(element)}
    }

    private fun calculateFuelRecursively(mass: Int): Int {
        val fuelRequired = calculateForModule(mass)
        return if(fuelRequired <= 0) {
            0
        } else {
            fuelRequired + calculateFuelRecursively(fuelRequired)
        }
    }
}
