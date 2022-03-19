package homework1.task1.eratosthenesSieve.benchmarks

import homeworks.homework1.task1.eratosthenesSieve.EratosthenesSieveFunctionally
import homeworks.homework1.task1.eratosthenesSieve.EratosthenesSieveImperative
import org.openjdk.jmh.annotations.*

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
open class CompareImperativeAndFunctionally {
    @Benchmark
    fun functionallyImplementation() {
        esf.getPrimesUpToTheBoundary(testValue)
    }

    @Benchmark
    fun imperativeImplementation() {
        esi.getPrimesUpToTheBoundary(testValue)
    }

    companion object {
        private val esf = EratosthenesSieveFunctionally()
        private val esi = EratosthenesSieveImperative()
        private const val testValue = 10000
    }
}
