package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.commandStorage.CommandStorage
import homeworks.homework1.task3.commandStorage.actions.PushBackAction

class PushBackHandler(private val commandStorage: CommandStorage) : CLIHandler() {
    override fun handle(arguments: List<String>) {
        try {
            val (number) = this.extractArguments(arguments, arrayOf(intCast))
            this.commandStorage.apply(PushBackAction(number as Int))
        } catch (e: InvalidArgumentsException) {
            println("Oops... ${e.localizedMessage}")
        }
    }
}
