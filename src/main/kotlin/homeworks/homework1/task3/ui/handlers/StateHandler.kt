package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.commandStorage.CommandStorage

class StateHandler(private val commandStorage: CommandStorage) : CLIHandler() {
    override fun handle(arguments: List<String>) {
        println("State: ${commandStorage.getElements().joinToString(", ")}")
    }
}
