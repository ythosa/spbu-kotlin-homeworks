package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.commandStorage.CommandStorage
import homeworks.homework1.task3.commandStorage.actions.ActionExecutionException
import homeworks.homework1.task3.commandStorage.actions.MoveAction

class MoveHandler(private val commandStorage: CommandStorage) : Handler() {
    override fun handle(arguments: List<String>) {
        try {
            val (from, to) = this.extractArguments(arguments, arrayOf(intCast, intCast))
            this.commandStorage.apply(MoveAction(from as Int, to as Int))
        } catch (e: InvalidArgumentsException) {
            println("Oops... ${e.localizedMessage}")
        } catch (e: ActionExecutionException) {
            println("Oops... ${e.localizedMessage}")
        }
    }
}
