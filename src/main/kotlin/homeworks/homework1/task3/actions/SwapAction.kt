package homeworks.homework1.task3.actions

import java.util.Collections.swap
import java.util.LinkedList

class SwapAction(private val firstIndex: Int, private val secondIndex: Int) : Action {
    override fun apply(elements: LinkedList<Int>) {
        for (index in listOf(firstIndex, secondIndex)) {
            if (!isIndexExists(index, elements)) {
                throw ActionExecutionException("error: can't swap with such index=$index")
            }
        }

        swap(elements, firstIndex, secondIndex)
    }

    override fun cancel(elements: LinkedList<Int>) {
        swap(elements, firstIndex, secondIndex)
    }

    private fun isIndexExists(index: Int, elements: List<Int>) = index >= 0 && index < elements.size
}
