package homeworks.homework1.task2.primeNumbers

interface PrimeNumbersExtractor {
    fun getPrimesUpToTheBoundary(bound: Int): List<Int>
}
