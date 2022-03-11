package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.commandStorage.actions.Action

interface CommandStorage {
    fun apply(action: Action)
    fun cancel()
    fun getElements(): List<Int>
}
