package homeworks.homework1.task2.primeNumbers.eratosthenesSieve

import homeworks.homework1.task2.primeNumbers.PrimeNumbersExtractor

class EratosthenesSieveImperative : PrimeNumbersExtractor {
    override fun getPrimesUpToTheBoundary(bound: Int): List<Int> {
        if (bound <= 0) {
            throw InvalidBoundException(bound)
        }

        val isPrimes = MutableList(bound) { index -> index > 1 }

        var number = 2
        while (number * number <= bound) {
            if (isPrimes[number]) {
                for (i in number * number until bound step number) {
                    isPrimes[i] = false
                }
            }
            number++
        }

        return isPrimes.mapIndexedNotNull { index, isPrime -> if (isPrime) index else null }
    }
}
