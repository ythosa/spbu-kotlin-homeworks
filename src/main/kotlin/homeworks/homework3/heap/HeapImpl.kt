package homeworks.homework3.heap

class HeapImpl<T : Comparable<T>> {
    private var size = 0
    private val elements = mutableListOf<T>()

    fun insert(element: T) {
        elements.add(element)
        siftUp(size++)
    }

    fun remove(): T? {
        if (size == 0)
            return null

        val minElement = elements[0]
        elements[0] = elements[--size]
        siftDown(0)

        return minElement
    }

    private fun siftUp(index: Int) {
        var i = index
        while (i > 0 && elements[i] < elements[(i - 1) / 2]) {
            elements[i] = elements[(i - 1) / 2].also { elements[(i - 1) / 2] = elements[i] }
            i = (i - 1) / 2
        }
    }

    private fun siftDown(index: Int) {
        var i = index
        while (true) {
            var j = i

            (2 * i + 1..2 * i + 2).forEach {
                if (it < size && elements[it] < elements[j])
                    j = it
            }

            if (j == i)
                break

            elements[i] = elements[j].also { elements[j] = elements[i] }
            i = j
        }
    }

    companion object {

    }
}
