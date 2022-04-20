package homeworks.homework4

import homeworks.homework4.gen.RandomListGenerator
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.LomutoPartition
import java.util.concurrent.ForkJoinPool

@Suppress("MagicNumber")
fun main() {
    val l = RandomListGenerator.build {
        minValue = 0
        maxValue = 1000
        elementsCount = 1000
    }.generate().toMutableList()

    val qSort = QSortThreadPool(executorService = ForkJoinPool(10))
    qSort.sorted(l, LomutoPartition())
}
