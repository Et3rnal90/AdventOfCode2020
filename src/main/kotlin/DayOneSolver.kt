import java.io.BufferedReader
import java.io.InputStreamReader

class DayOneSolver {

    val input : List<Int>

    init {
        val problemStream = javaClass.getResource("input1.txt").openStream()
        val buf = BufferedReader(InputStreamReader(problemStream))
        input = buf.readLines().map { it.toInt() }
    }

    fun solvePart1() {
        println("Day 1, part 1")
        println("Solving...")
        for ((i, firstEntry) in input.withIndex()) {
            for ( secondEntry in input.drop(i+1)) {
                if (firstEntry + secondEntry == 2020) {
                    println("- Answer found for ${firstEntry}, ${secondEntry}.\n" +
                            "Multiplication is: ${firstEntry * secondEntry}")
                }
            }
        }
        println("Day 1 solution completed.")
    }

    fun solvePart2() {
        println("Day 1, part 2.")
        println("Solving...")
        for ((i, firstEntry) in input.withIndex()) {
            for ((j, secondEntry) in input.drop(i+1).withIndex()) {
                for (thirdEntry in input.drop(i+j)) {
                    if (firstEntry + secondEntry + thirdEntry == 2020) {
                        println("- Answer found for $firstEntry, $secondEntry, $thirdEntry.\n" +
                                "Multiplication is: ${firstEntry * secondEntry * thirdEntry}")
                    }
                }
            }
        }
        println("Day 1, part 2 solution completed.")
    }
}