package homeworks.homework1.task3.ui.handlers

import homeworks.homework1.task3.commandStorage.CommandStorage
import homeworks.homework1.task3.commandStorage.ImpossibleCancelCommandException

class CancelHandler(private val commandStorage: CommandStorage) : CLIHandler() {
    override fun handle(arguments: List<String>) {
        try {
            this.commandStorage.cancel()
        } catch (e: ImpossibleCancelCommandException) {
            println("Oops... ${e.localizedMessage}")
        }
    }
}
