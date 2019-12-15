package me.wedrowicz.adventofcode.day14

fun parse(fileName: String): List<ChemicalReaction> {
    val fileContent = {}.javaClass.classLoader.getResource(fileName)!!.readText(Charsets.UTF_8)
    return fileContent.trim().split("\n")
        .map { el -> el.split(",", "=>").map { el1 -> el1.trim() }.map { el2 -> val elements = el2.split(" "); Ingredient(elements[1], elements[0].toLong()) } }
        .map { ingredientList -> ChemicalReaction(ingredientList.subList(0, ingredientList.size-1), ingredientList.last())}
}
