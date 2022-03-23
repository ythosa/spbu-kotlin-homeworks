package homeworks.homework1.task3.commandStorage.actions

class PushFirstAction(private val element: Int) : Action {
    override fun apply(elements: MutableList<Int>) {
        elements.add(0, element)
    }

    override fun cancel(elements: MutableList<Int>) {
        elements.removeFirst()
    }
}
