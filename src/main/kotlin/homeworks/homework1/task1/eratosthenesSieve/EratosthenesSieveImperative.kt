package homeworks.homework1.task1.eratosthenesSieve

import kotlin.math.sqrt

class EratosthenesSieveImperative : EratosthenesSieve {
    override fun getPrimesUpToTheBoundary(bound: Int): List<Int> {
        if (bound <= 0)
            throw InvalidBoundException(bound)

        val sieveArray = getSieveArray(bound)
        sieve(sieveArray, bound)

        return extractPrimesFromSieveArray(sieveArray)
    }

    private fun extractPrimesFromSieveArray(sieveArray: MutableList<Boolean>): List<Int> =
        sieveArray.mapIndexedNotNull { index, isPrime -> if (isPrime) index else null }

    private fun sieve(sieveArray: MutableList<Boolean>, bound: Int) {
        for (number in 2..intSqrt(bound)) {
            if (sieveArray[number]) {
                for (i in number * number until bound step number) {
                    sieveArray[i] = false
                }
            }
        }
    }

    private fun getSieveArray(bound: Int) = MutableList(bound) { index -> index !in 0..1 }

    private fun intSqrt(n: Int) = sqrt(n.toFloat()).toInt()
}
