package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition

interface QSort{
    fun <T : Comparable<T>> sorted(list: MutableList<T>, partition: Partition<T>)
}
