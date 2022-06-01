package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition

class QSortSequential<T : Comparable<T>>(private val partition: Partition<T>) : QSort<T> {
    override fun sorted(list: MutableList<T>) {
        sort(list)
    }

    private fun sort(
        list: MutableList<T>,
        lowIndex: Int = 0,
        highIndex: Int = list.size - 1
    ) {
        if (lowIndex < highIndex) {
            val partitionIndices = partition.apply(list, lowIndex, highIndex)
            sort(list, lowIndex, partitionIndices.leftToIndex)
            sort(list, partitionIndices.rightFromIndex, highIndex)
        }
    }
}
