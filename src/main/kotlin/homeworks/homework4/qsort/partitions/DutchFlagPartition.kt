package homeworks.homework4.qsort.partitions

import homeworks.homework4.qsort.swapAt

class DutchFlagPartition<T : Comparable<T>> : Partition<T> {
    override fun apply(list: MutableList<T>, lowIndex: Int, highIndex: Int): PartitionResult {
        val pivot = list[highIndex]
        var smaller = lowIndex
        var equal = lowIndex
        var larger = highIndex
        while (equal <= larger) {
            if (list[equal] < pivot) {
                list.swapAt(smaller, equal)
                smaller += 1
                equal += 1
            } else if (list[equal] == pivot) {
                equal += 1
            } else {
                list.swapAt(equal, larger)
                larger -= 1
            }
        }
        return PartitionResult(smaller - 1, larger + 1)
    }
}
