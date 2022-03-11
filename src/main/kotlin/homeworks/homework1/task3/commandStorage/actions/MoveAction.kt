package homeworks.homework1.task3.commandStorage.actions

class MoveAction(private val firstIndex: Int, private val secondIndex: Int) : Action {
    override fun apply(elements: MutableList<Int>) {
        for (index in listOf(firstIndex, secondIndex)) {
            if (!isIndexExists(index, elements)) {
                throw ActionExecutionException("error: can't move with such index=$index")
            }
        }

        move(elements, firstIndex, secondIndex)
    }

    override fun cancel(elements: MutableList<Int>) {
        move(elements, secondIndex, firstIndex)
    }

    private fun move(elements: MutableList<Int>, from: Int, to: Int) {
        val element = elements[from]
        elements.removeAt(from)
        elements.add(to, element)
    }

    private fun isIndexExists(index: Int, elements: MutableList<Int>) = index >= 0 && index < elements.size
}
