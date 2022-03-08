package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.actions.Action
import homeworks.homework1.task3.actions.MoveAction

class MoveStrategy : ParsingStrategy {
    override fun parse(arguments: List<String>): Action {
        if (arguments.size != argumentsCount) {
            throw InvalidArgumentsException(expected = argumentsCount, actual = arguments.size)
        }

        val mapped = arguments.mapNotNull { it.toIntOrNull() }
        if (mapped.size != argumentsCount) {
            throw InvalidArgumentsException(expected = "integer")
        }

        return MoveAction(mapped[0], mapped[1])
    }

    override fun getName(): String = "move"

    companion object {
        private const val argumentsCount = 2
    }
}
