import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

class DayTwoSolver {

    var input : List<String>

    init {
        val resStream = javaClass.getResource("input2.txt").openStream()
        val buf = BufferedReader(InputStreamReader(resStream))
        input = buf.readLines()
    }

    fun solvePart1() {
        println("---\nDay 2, part 1")
        println("Solving...")

        var correctPasswords = 0
        for (entry in input) {
            val regex = "([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)".toRegex()
            regex.matchEntire(entry)
                    ?.destructured
                    ?.let { (minOcc, maxOcc, occChar, pass) ->
                        val ruledPass = OccurrenceRulePassword(minOcc.toInt(), maxOcc.toInt(), occChar[0], pass)
                        if (ruledPass.validate()) {
                            println("- Found valid password: $pass")
                            correctPasswords++
                        }
                    }
                    ?: throw IllegalArgumentException("Bad regex for text: $entry")
        }
        println("Correct passwords found: $correctPasswords")

        println("Day 2, part 1 solution completed.\n---")
    }

    fun solvePart2() {
        println("---\nDay 2, part 2")
        println("Solving...")
        var correctPasswords = 0
        for (entry in input) {
            val regex = "([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)".toRegex()
            regex.matchEntire(entry)
                    ?.destructured
                    ?.let { (minOcc, maxOcc, occChar, pass) ->
                        val ruledPass = PositionRulePassword(minOcc.toInt(), maxOcc.toInt(), occChar[0], pass)
                        if (ruledPass.validate()) {
                            println("- Found valid password: $pass")
                            correctPasswords++
                        }
                    }
                    ?: throw IllegalArgumentException("Bad regex for text: $entry")
        }
        println("Correct passwords found: $correctPasswords")
        println("Day 2, part 2 solution completed.\n---")
    }

    data class OccurrenceRulePassword (
        val minOccurrences : Int,
        val maxOccurrences : Int,
        val occurrentChar : Char,
        val password : String
    ) {
        fun validate() : Boolean {
            var numOccurrences = 0
            password.map {
                if (it == occurrentChar) numOccurrences++
            }
            return (minOccurrences..maxOccurrences).contains(numOccurrences)
        }
    }

    data class PositionRulePassword(
            val firstPosition : Int,
            val secondPosition : Int,
            val occurrentChar : Char,
            val password : String
    ) {
        fun validate(): Boolean {
            return (password[firstPosition-1] == occurrentChar).xor(password[secondPosition-1] == occurrentChar)
        }
    }
}