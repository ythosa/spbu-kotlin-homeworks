package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.commandStorage.actions.Action
import java.util.LinkedList

class PerformedCommandStorage : CommandStorage {
    private val commands = ArrayDeque<Action>()
    private val elements = LinkedList<Int>()

    override fun apply(action: Action) {
        action.apply(elements)
        commands.add(action)
    }

    override fun cancel() {
        commands.removeLastOrNull()?.cancel(elements) ?: throw ImpossibleCancelCommandException()
    }

    override fun getElements(): List<Int> {
        return elements
    }
}
