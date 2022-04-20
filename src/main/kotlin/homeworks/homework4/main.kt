package homeworks.homework4

import homeworks.homework4.gen.RandomListGenerator
import homeworks.homework4.qsort.QSort
import homeworks.homework4.qsort.partitions.HoarePartition

@Suppress("MagicNumber")
fun main() {
    val l = RandomListGenerator.build {
        minValue = 0
        maxValue = 1000
        elementCount = 1000
    }.generate().toMutableList()

    println(QSort(list = l, partition = HoarePartition()).getSorted())
}
