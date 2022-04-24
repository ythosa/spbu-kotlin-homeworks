package homeworks.homework4

import homeworks.homework4.bench.QSortBenchmark
import homeworks.homework4.gen.RandomListOfIntsGenerator
import homeworks.homework4.qsort.QSort
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.DutchFlagPartition
import homeworks.homework4.qsort.partitions.HoarePartition
import homeworks.homework4.qsort.partitions.LomutoPartition
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ForkJoinPool
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun main() {
    val data = getDataFrame()
    println(data)

    val p = ggplot(data) { y = "durations"; x = "elementsCount"; color = "names" } +
            ggsize(2000, 1500) +
            geomPoint() +
            geomLine()
    //            geomSmooth()

    ggsave(p, "density.png")
}

data class Bench(val name: String, val elementsCount: Int, val duration: Duration)

fun getDataFrame(): MutableMap<String, Any> {
    val data = mutableListOf<Bench>()

    val from = 100_000
    val to = 1_000_000
    val step = 100_000

    for (count in from..to step step) {
        val generator = RandomListOfIntsGenerator.build {
            minValue = 0
            maxValue = 10_000_000
            elementsCount = count
        }
        val benchmark = QSortBenchmark(1, 0, generator)
        val qSorts = mapOf<String, QSort<Int>>(
            "coroutines with Lomuto" to QSortCoroutines(LomutoPartition(), ForkJoinPool().asCoroutineDispatcher()),
            "coroutines with Hoare" to QSortCoroutines(HoarePartition(), ForkJoinPool().asCoroutineDispatcher()),
            "coroutines with DutchFlag" to QSortCoroutines(DutchFlagPartition(), ForkJoinPool().asCoroutineDispatcher()),
            "thread pool with Lomuto" to QSortThreadPool(LomutoPartition(), ForkJoinPool()),
            "thread pool with Hoare" to QSortThreadPool(HoarePartition(), ForkJoinPool()),
            "thread pool with DutchFlag" to QSortThreadPool(DutchFlagPartition(), ForkJoinPool()),
            "single thread with Lomuto" to QSortSequential(LomutoPartition()),
            "single thread with Hoare" to QSortSequential(HoarePartition()),
            "single thread with DutchFlag" to QSortSequential(DutchFlagPartition()),
        )

        data.addAll(benchmark.benchAll(qSorts).map { Bench(it.key, count, it.value) })
    }

    data.sortBy { it.duration }

    val result = mutableMapOf<String, Any>()

    result["names"] = data.map { it.name }
    result["durations"] = data.map { it.duration.toInt(DurationUnit.MILLISECONDS) }
    result["elementsCount"] = data.map { it.elementsCount }

    return result
}

fun main11() {
    val generator = RandomListOfIntsGenerator.build {
        minValue = 0
        maxValue = 10_000_000
        elementsCount = 5_000_000
    }
    val benchmark = QSortBenchmark(3, 5, generator)
    val qSorts = mapOf<String, QSort<Int>>(
        "coroutines with Lomuto" to QSortCoroutines(LomutoPartition(), ForkJoinPool().asCoroutineDispatcher()),
        "coroutines with Hoare" to QSortCoroutines(HoarePartition(), ForkJoinPool().asCoroutineDispatcher()),
        "coroutines with DutchFlag" to QSortCoroutines(DutchFlagPartition(), ForkJoinPool().asCoroutineDispatcher()),
        "thread pool with Lomuto" to QSortThreadPool(LomutoPartition(), ForkJoinPool()),
        "thread pool with Hoare" to QSortThreadPool(HoarePartition(), ForkJoinPool()),
        "thread pool with DutchFlag" to QSortThreadPool(DutchFlagPartition(), ForkJoinPool()),
        "single thread with Lomuto" to QSortSequential(LomutoPartition()),
        "single thread with Hoare" to QSortSequential(HoarePartition()),
        "single thread with DutchFlag" to QSortSequential(DutchFlagPartition()),
    )

    val benchmarkResult = benchmark.benchAll(qSorts)

    for ((name, duration) in benchmarkResult) {
        println("$name: $duration")
    }
}
