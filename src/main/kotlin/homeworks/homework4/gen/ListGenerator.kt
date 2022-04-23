package homeworks.homework4.gen

interface ListGenerator<T : Comparable<T>> {
    fun generate(): List<T>
}
