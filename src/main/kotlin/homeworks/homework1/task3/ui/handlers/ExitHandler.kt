package homeworks.homework1.task3.ui.handlers

import kotlin.system.exitProcess

class ExitHandler : Handler() {
    override fun handle(arguments: List<String>) {
        println("ʕ•́ᴥ•̀ʔっ Goodbye!")
        exitProcess(0)
    }
}
