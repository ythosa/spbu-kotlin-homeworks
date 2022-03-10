package homeworks.homework1.task3.commandStorage.actions

import java.util.LinkedList

class MoveAction(private val firstIndex: Int, private val secondIndex: Int) : Action {
    override fun apply(elements: LinkedList<Int>) {
        for (index in listOf(firstIndex, secondIndex)) {
            if (!isIndexExists(index, elements)) {
                throw ActionExecutionException("error: can't move with such index=$index")
            }
        }

        move(elements, firstIndex, secondIndex)
    }

    override fun cancel(elements: LinkedList<Int>) {
        move(elements, secondIndex, firstIndex)
    }

    private fun move(elements: LinkedList<Int>, from: Int, to: Int) {
        val element = elements[from]
        elements.removeAt(from)
        elements.add(to, element)
    }

    private fun isIndexExists(index: Int, elements: List<Int>) = index >= 0 && index < elements.size
}
