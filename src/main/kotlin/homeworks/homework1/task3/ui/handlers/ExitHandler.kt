package homeworks.homework1.task3.ui.handlers

import kotlin.system.exitProcess

class ExitHandler : CLIHandler() {
    override fun handle(arguments: List<String>) {
        println("ʕ•́ᴥ•̀ʔっ Goodbye!")
        exitProcess(0)
    }
}
