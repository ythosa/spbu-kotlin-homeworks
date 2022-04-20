package homeworks.homework4.qsort.partitions

interface Partition<T : Comparable<T>> {
    fun applyPartition(list: MutableList<T>, lowIndex: Int, highIndex: Int): Int
}
