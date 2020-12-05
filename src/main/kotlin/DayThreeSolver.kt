import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

class DayThreeSolver {

    var input : List<String>

    init {
        val resStream = javaClass.getResource("input3.txt").openStream()
        val buf = BufferedReader(InputStreamReader(resStream))
        input = buf.readLines()
    }

    fun solvePart1() {
        println("---\nDay 3, part 1")
        println("Solving...")

        val field = TreeField(input)
        val treesInTheWay = field.tracePath(3, 1)

        println("Went down the field on right 3, down 1, and encountered $treesInTheWay trees in the way.")
        println("Day 3, part 1 solution completed.\n---")
    }

    fun solvePart2() {
        println("---\nDay 3, part 2")
        println("Solving...")

        val field = TreeField(input)

        val treesInTheWay1 = field.traceLongPath(1, 1)
        println("Went down the field on right 1, down 1, and encountered $treesInTheWay1 trees in the way.")

        val treesInTheWay2 = field.traceLongPath(3, 1)
        println("Went down the field on right 3, down 1, and encountered $treesInTheWay2 trees in the way.")

        val treesInTheWay3 = field.traceLongPath(5, 1)
        println("Went down the field on right 5, down 1, and encountered $treesInTheWay3 trees in the way.")

        val treesInTheWay4 = field.traceLongPath(7, 1)
        println("Went down the field on right 7, down 1, and encountered $treesInTheWay4 trees in the way.")

        val treesInTheWay5 : Long = field.traceLongPath(1, 2)
        println("Went down the field on right 1, down 2, and encountered $treesInTheWay5 trees in the way.")

        val multiTreesInTheWay = treesInTheWay1 * treesInTheWay2 * treesInTheWay3 * treesInTheWay4 * treesInTheWay5
        println("- In the end, all found trees multiplied together are $multiTreesInTheWay.")

        println("Day 3, part 2 solution completed.\n---")
    }

    class TreeField (val field: List<String>) {

        fun tracePath(right: Int, down: Int) : Int {
            var x = right
            var y = down
            var numTrees = 0
            if (y < 0 || x < 0) throw IllegalArgumentException("right and down must be non-negative")
            while ( y < field.count() ) {
                val cell = getCellAt(x, y)
                if ( isTree(cell) ) numTrees ++
                x += right
                y += down
            }
            return numTrees
        }

        fun traceLongPath(right: Int, down: Int) : Long {
            var x = right
            var y = down
            var numTrees = 0L
            if (y < 0 || x < 0) throw IllegalArgumentException("right and down must be non-negative")
            while ( y < field.count() ) {
                val cell = getCellAt(x, y)
                if ( isTree(cell) ) numTrees ++
                x += right
                y += down
            }
            return numTrees
        }

        private fun getCellAt(x: Int, y: Int) : Char {
            val row = field[y]
            return row[x % row.length]
        }

        private fun isTree(cell: Char) : Boolean {
            return cell == '#'
        }
    }
}