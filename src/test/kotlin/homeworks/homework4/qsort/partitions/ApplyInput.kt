package homeworks.homework4.qsort.partitions

data class ApplyInput<T: Comparable<T>>(
    val list: MutableList<T>,
    val lowIndex: Int,
    val highIndex: Int
)
