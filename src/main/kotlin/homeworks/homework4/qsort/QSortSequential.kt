package homeworks.homework4.qsort

import homeworks.homework4.qsort.partitions.Partition

class QSortSequential : QSort {
    override fun <T : Comparable<T>> sorted(list: MutableList<T>, partition: Partition<T>) {
        sort(list, partition)
    }

    private fun <T : Comparable<T>> sort(
        list: MutableList<T>,
        partition: Partition<T>,
        lowIndex: Int = 0,
        highIndex: Int = list.size - 1
    ) {
        if (lowIndex < highIndex) {
            val pivotIndex = partition.apply(list, lowIndex, highIndex)
            sort(list, partition, lowIndex, pivotIndex - 1)
            sort(list, partition, pivotIndex + 1, highIndex)
        }
    }
}
