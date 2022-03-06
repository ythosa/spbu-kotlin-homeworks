package homework1.task1.eratosthenesSieve

class EratosthenesSieveFunctionally : EratosthenesSieve {
    override fun getPrimesUpToTheBoundary(bound: Int): List<Int> {
        if (bound <= 0)
            throw InvalidBoundException(bound)

        val numbers = generateSequence(2) { it + 1 }

        return sieve(numbers).takeWhile { it < bound }.toList()
    }

    private fun sieve(numbers: Sequence<Int>): Sequence<Int> = sequence {
        val head = numbers.first()
        val tail = numbers.drop(1).filter { it % head != 0 }

        yield(head)

        for (number in sieve(tail))
            yield(number)
    }
}
