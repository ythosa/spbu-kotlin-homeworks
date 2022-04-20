package homeworks.homework4.qsort.partitions

interface Partition<T : Comparable<T>> {
    fun apply(list: MutableList<T>, lowIndex: Int, highIndex: Int): Int
}
