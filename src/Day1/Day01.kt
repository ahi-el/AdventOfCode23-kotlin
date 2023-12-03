package Day1

import println
import readInput

/** Returns a list of all possible tail substrings of a given string. */
private fun String.tails(): List<CharSequence> = indices.map { subSequence(it, length) }

/** Mapping from "0" -> 0, "1" -> 1 etc. */
private val numericDigits: Map<String, Int> = (0..9).associateBy { it.toString() }

/** Mapping from "one" -> 1, "two" -> 2 etc. */
private val writtenDigits: Map<String, Int> = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    .withIndex().associate { (i, word) -> word to (i + 1) }

private fun CharSequence.startingDigit(digits: Map<String, Int>): Int? =
    digits.entries.firstNotNullOfOrNull { (prefix, digit) -> digit.takeIf { startsWith(prefix) } }

/** Calculates the calibration value for a line, using given digit mappings */
private fun calibrationValue(line: String, digits: Map<String, Int>): Int {
    val lineDigits = line.tails().mapNotNull { it.startingDigit(digits) }
    return lineDigits.first() * 10 + lineDigits.last()
}

private fun calibrationValueSum(input: List<String>, digits: Map<String, Int>) =
    input.sumOf { calibrationValue(it, digits) }

fun main() {
    fun part1(input: List<String>) : Int = calibrationValueSum(input, digits = numericDigits)
    fun part2(input: List<String>) : Int = calibrationValueSum(input, digits = numericDigits + writtenDigits)


    val testInput = readInput("Day01_test")

    val testOutput = part1(testInput)
    println(testOutput)
    check(testOutput == 142)

    val testInput2 = readInput("Day02_test")

    val testOutput2 = part2(testInput2)
    println(testOutput2)
    check(testOutput2 == 281)
//    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
