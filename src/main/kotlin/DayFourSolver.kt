import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

class DayFourSolver {

    var input : List<String>

    init {
        val resStream = javaClass.getResource("input4.txt").openStream()
        val buf = BufferedReader(InputStreamReader(resStream))
        input = buf.readLines()
    }

    fun solvePart1() {
        println("---\nDay 4, part 1")
        println("Solving...")

        val reader = PassportReader(input)
        val validPassports = reader.validateBatch()

        println("Found $validPassports valid passports.")
        println("Day 2, part 1 solution completed.\n---")
    }

    fun solvePart2() {
        println("---\nDay 4, part 2")
        println("Solving...")

        val reader = StricterPassportReader(input)
        val validPassports = reader.validateBatch()

        println("Found $validPassports valid passports.")
        println("Day 2, part 2 solution completed.\n---")
    }

    class StricterPassportReader(batch: List<String>) {

        val passports : MutableList<StricterPassport> = mutableListOf()

        init {
            var info : MutableMap<String, String> = mutableMapOf()
            for (entry in batch) {
                if (entry == "") {
                    passports.add(StricterPassport(info))
                    info = mutableMapOf()
                } else {
                    val regex = "([a-z]{3}):([a-z0-9#]+) ?".toRegex()
                    var result = regex.find(entry)
                    do {
                        result?.destructured?.let { (key, value) ->
                            info[key] = value
                        }
                        result = result?.next()
                    } while (result != null)
                }
            }
            if (info.isNotEmpty()) {
                passports.add(StricterPassport(info))
            }
        }

        fun validateBatch() : Int {
            var numValidPassports = 0
            for (passport in passports) {
                if (passport.isValid()) numValidPassports++
            }
            return numValidPassports
        }

        class StricterPassport(val info: Map<String, String>) {
            fun isValid() : Boolean {
                if (!(info.count() == 8 || (info.count() == 7 && !info.keys.contains("cid")))) return false
                info["byr"]?.let {
                    val value = it.toInt()
                    val count = it.count()
                    if (count == 4 && (value < 1920 && value > 2002)) return false
                }
                info["iyr"]?.let {
                    val value = it.toInt()
                    val count = it.count()
                    if (count == 4 && (value < 2010 && value > 2020)) return false
                }
                info["eyr"]?.let {
                    val value = it.toInt()
                    val count = it.count()
                    if (count == 4 && (value < 2020 && value > 2030)) return false
                }
                info["hgt"]?.let {
                    val regex = "[0-9]+(cm|in)".toRegex()
                    if (!regex.matches(it)) return false
                    val unit = it.substring(it.count() - 2)
                    val value = it.substring(0, it.count() - 2).toInt()
                    if (unit == "cm") {
                        if (value < 150 && value > 193) return false
                    } else if (unit == "in") {
                        if (value < 59 && value > 76) return false
                    } else return false
                }
                info["hcl"]?.let {
                    val regex = "#[a-f0-9]{6}".toRegex()
                    if (!regex.matches(it)) return false
                }
                info["ecl"]?.let {
                    val cases = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                    if (!cases.contains(it)) return false
                }
                info["pid"]?.let {
                    val regex = "[0-9]{9}".toRegex()
                    if (!regex.matches(it)) return false
                }
                return true
            }
        }
    }

    class PassportReader(batch: List<String>) {

        val passports : MutableList<Passport> = mutableListOf()

        init {
            var info : MutableMap<String, String> = mutableMapOf()
            for (entry in batch) {
                if (entry == "") {
                    passports.add(Passport(info))
                    info = mutableMapOf()
                } else {
                    val regex = "([a-z]{3}):([a-z0-9#]+) ?".toRegex()
                    var result = regex.find(entry)
                    do {
                        result?.destructured?.let { (key, value) ->
                            info[key] = value
                        }
                        result = result?.next()
                    } while (result != null)
                }
            }
            if (info.isNotEmpty()) {
                passports.add(Passport(info))
            }
        }

        fun validateBatch() : Int {
            var numValidPassports = 0
            for (passport in passports) {
                if (passport.isValid()) numValidPassports++
            }
            return numValidPassports
        }

        class Passport(val info: Map<String, String>) {

            fun isValid() : Boolean {
                val regularPassport = info.count() == 8
                val northPoleCredential = info.count() == 7 && !info.keys.contains("cid")

                return regularPassport || northPoleCredential
            }
        }

        class StricterPassport(val info: Map<String, String>) {
            fun isValid() : Boolean {
                val regularPassport = info.count() == 8
                val northPoleCredential = info.count() == 7 && !info.keys.contains("cid")

                return regularPassport || northPoleCredential
            }
        }
    }
}