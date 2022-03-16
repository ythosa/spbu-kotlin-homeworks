package homeworks.homework1.task3.commandStorage.actions

class MoveAction(private val from: Int, private val to: Int) : Action {
    override fun apply(elements: MutableList<Int>) {
        if (from !in elements.indices) {
            throw ActionExecutionException("error: can't move from index=$from")
        }

        if (to !in elements.indices) {
            throw ActionExecutionException("error: can't move to index=$to")
        }

        move(elements, from, to)
    }

    override fun cancel(elements: MutableList<Int>) {
        move(elements, to, from)
    }

    private fun move(elements: MutableList<Int>, from: Int, to: Int) {
        val element = elements[from]
        elements.removeAt(from)
        elements.add(to, element)
    }
}
