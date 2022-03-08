package homeworks.homework1.task3.ui

import homeworks.homework1.task3.actions.ActionExecutionException
import homeworks.homework1.task3.commandStorage.ImpossibleCancelCommandException
import homeworks.homework1.task3.commandStorage.PerformedCommandStorage
import homeworks.homework1.task3.ui.parser.InvalidArgumentsException
import homeworks.homework1.task3.ui.parser.InvalidCommandException
import homeworks.homework1.task3.ui.parser.Parser
import kotlin.system.exitProcess

class REPL(
    private val commandStorage: PerformedCommandStorage,
    private val parser: Parser
) {
    fun start() {
        welcomeHandler()
        helpHandler()

        while (true) {
            print(">>> ")
            handleCommand(readln().trim())
        }
    }

    private fun handleCommand(line: String) = when (line) {
        "help" -> helpHandler()
        "cancel" -> cancelHandler()
        "exit" -> exitHandler()
        else -> {
            try {
                commandStorage.apply(parser.parse(line))
                printStorageElements()
            } catch (e: InvalidCommandException) {
                println("Oops... parsing command error: $e")
            } catch (e: InvalidArgumentsException) {
                println("Oops... parsing arguments error: $e")
            } catch (e: ActionExecutionException) {
                println("Oops... execution command error: $e")
            }
        }
    }

    private fun welcomeHandler() {
        println("ʕ•́ᴥ•̀ʔっ Welcome to Performed Command Storage! ")
    }

    private fun helpHandler() {
        println(
            """Commands:
            * help - returns help message
            * exit - exit from command
            * cancel - cancel last command
            * push <Int> - add integer element to the end
            * unshift <Int> - add integer element to the start
            * swap first<Int> second<Int> - swaps two elements with indexes `first` and `second`""".trimIndent()
        )
    }

    private fun cancelHandler() {
        try {
            commandStorage.cancel()
            printStorageElements()
        } catch (e: ImpossibleCancelCommandException) {
            println("Oops... $e")
        }
    }

    private fun exitHandler() {
        println("ʕ•́ᴥ•̀ʔっ Goodbye! ")
        exitProcess(0)
    }

    private fun printStorageElements() {
        val elements = commandStorage.getElements()
        val state = if (elements.isEmpty()) "<empty>" else elements.joinToString(", ")

        println("State: $state")
    }
}
