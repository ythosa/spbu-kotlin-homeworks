package homeworks.homework1.task3.commandStorage.actions

interface Action {
    fun apply(elements: MutableList<Int>)
    fun cancel(elements: MutableList<Int>)
}
