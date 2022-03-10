package homeworks.homework1.task3.commandStorage.actions

import java.util.LinkedList

interface Action {
    fun apply(elements: LinkedList<Int>)
    fun cancel(elements: LinkedList<Int>)
}
