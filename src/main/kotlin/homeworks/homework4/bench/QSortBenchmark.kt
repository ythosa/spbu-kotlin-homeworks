package homeworks.homework4.bench

import homeworks.homework4.gen.ListGenerator
import homeworks.homework4.qsort.QSort
import mu.KotlinLogging
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class QSortBenchmark<T : Comparable<T>>(
    private val repetitionsNumber: Int,
    private val warmUpsNumber: Int,
    private val listGenerator: ListGenerator<T>,
) {
    private val logger = KotlinLogging.logger(QSortBenchmark::class.simpleName.toString())

    @OptIn(ExperimentalTime::class)
    fun bench(name: String, qSort: QSort<T>): Duration {
        logger.info("benching $name")

        val lists = generateSequence(listGenerator.generate().toMutableList()) {
            listGenerator.generate().toMutableList()
        }
        val times = lists.map { measureTime { qSort.sorted(it) } }.drop(warmUpsNumber).take(repetitionsNumber)

        return times.reduce { acc, duration -> acc + duration }.div(repetitionsNumber)
    }

    fun benchAll(qSorts: Map<String, QSort<T>>) = qSorts.mapValues { (name, qSort) -> bench(name, qSort) }
}
