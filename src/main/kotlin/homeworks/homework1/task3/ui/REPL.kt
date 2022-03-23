package homeworks.homework1.task3.ui

import homeworks.homework1.task3.ui.handlers.CLIHandler
import homeworks.homework1.task3.ui.parser.Command
import homeworks.homework1.task3.ui.parser.Parser

class REPL(private val parser: Parser, private val commands: Map<String, CLIHandler>) {
    fun start() {
        printWelcomeMessage()

        while (true) {
            print(">>> ")
            val inputCommand = parser.parse(readln())
            executeCommand(inputCommand)
        }
    }

    private fun executeCommand(inputCommand: Command) {
        for ((commandName, handler) in commands) {
            if (inputCommand.name == commandName) {
                return handler.handle(inputCommand.arguments)
            }
        }

        if (inputCommand.name.isEmpty())
            println("Oops.. you have to enter something")
        else
            println("Sorry, I do not know this command :(")
    }

    private fun printWelcomeMessage() = println("ʕ•́ᴥ•̀ʔっ Welcome to Performed Command Storage! ")
}
