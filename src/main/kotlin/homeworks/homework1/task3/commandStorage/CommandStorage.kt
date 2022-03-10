package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.commandStorage.actions.Action
import java.util.LinkedList

interface CommandStorage {
    fun apply(action: Action)
    fun cancel()
    fun getElements(): LinkedList<Int>
}
