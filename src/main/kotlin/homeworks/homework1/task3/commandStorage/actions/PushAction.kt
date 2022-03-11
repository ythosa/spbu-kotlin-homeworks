package homeworks.homework1.task3.commandStorage.actions

class PushAction(private val element: Int) : Action {
    override fun apply(elements: MutableList<Int>) {
        elements.add(element)
    }

    override fun cancel(elements: MutableList<Int>) {
        elements.removeLast()
    }
}
