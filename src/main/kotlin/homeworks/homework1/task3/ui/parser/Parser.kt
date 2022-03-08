package homeworks.homework1.task3.ui.parser

import homeworks.homework1.task3.actions.Action
import homeworks.homework1.task3.commandStorage.DuplicatedStrategyNamesException

class Parser(private val strategies: List<ParsingStrategy>) {
    init {
        if (strategies.groupBy { it.getName() }.size != strategies.size) {
            throw DuplicatedStrategyNamesException()
        }
    }

    fun parse(string: String): Action {
        val parts = string.split(" ")
        if (parts.isEmpty()) {
            throw InvalidCommandException("<empty>")
        }

        val commandName = parts.first()
        val arguments = parts.drop(1)
        for (strategy in strategies) {
            if (strategy.getName() == commandName) {
                return strategy.parse(arguments)
            }
        }

        throw InvalidCommandException(commandName)
    }
}
