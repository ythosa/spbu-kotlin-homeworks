package tests.test1.task1.priorityQueue

data class Element<E, K : Comparable<K>>(val element: E, val priority: K) : Comparable<Element<E, K>> {
    override fun compareTo(other: Element<E, K>): Int = when {
        this.priority < other.priority -> -1
        this.priority > other.priority -> 1
        else -> 0
    }
}
