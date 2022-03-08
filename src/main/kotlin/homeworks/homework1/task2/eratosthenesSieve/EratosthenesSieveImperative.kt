package homeworks.homework1.task2.eratosthenesSieve

import kotlin.math.sqrt

class EratosthenesSieveImperative : EratosthenesSieve {
    override fun getPrimesUpToTheBoundary(bound: Int): List<Int> {
        if (bound <= 0) {
            throw InvalidBoundException(bound)
        }

        val isPrimes = MutableList(bound) { index -> index !in 0..1 }
        sieve(isPrimes, bound)

        return extractPrimes(isPrimes)
    }

    private fun extractPrimes(isPrimes: MutableList<Boolean>): List<Int> =
        isPrimes.mapIndexedNotNull { index, isPrime -> if (isPrime) index else null }

    private fun sieve(isPrimes: MutableList<Boolean>, bound: Int) {
        for (number in 2..intSqrt(bound)) {
            if (isPrimes[number]) {
                for (i in number * number until bound step number) {
                    isPrimes[i] = false
                }
            }
        }
    }

    private fun intSqrt(n: Int) = sqrt(n.toFloat()).toInt()
}
