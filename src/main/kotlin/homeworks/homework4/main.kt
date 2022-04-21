package homeworks.homework4

import homeworks.homework4.gen.RandomListGenerator
import homeworks.homework4.qsort.QSortCoroutines
import homeworks.homework4.qsort.QSortSequential
import homeworks.homework4.qsort.QSortThreadPool
import homeworks.homework4.qsort.partitions.HoarePartition
import homeworks.homework4.qsort.partitions.LomutoPartition
import java.util.concurrent.ForkJoinPool

@Suppress("MagicNumber")
fun main() {
    val l = RandomListGenerator.build {
        minValue = 0
        maxValue = 1000
        elementsCount = 1000
    }.generate().toMutableList()

//    val qSort = QSortThreadPool(LomutoPartition(), ForkJoinPool(10))
    val qSort = QSortCoroutines<Int>(HoarePartition())
//    val qSort = QSortSequential(LomutoPartition())
    qSort.sorted(l)
    println(l == l.sorted())
}
