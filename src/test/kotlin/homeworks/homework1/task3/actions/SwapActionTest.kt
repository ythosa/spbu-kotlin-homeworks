package homeworks.homework1.task3.actions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.LinkedList

internal class SwapActionTest {
    @Test
    fun apply() {
        val elements = LinkedList(listOf(1, 2, 3))
        swapAction.apply(elements)
        assertEquals(elements, LinkedList(listOf(1, 3, 2)))
    }

    @Test
    fun `apply with invalid first index`() {
        val elements = LinkedList(listOf(1))
        assertThrows<ActionExecutionException> { swapAction.apply(elements) }
    }

    @Test
    fun `apply with invalid second index`() {
        val elements = LinkedList(listOf(1, 2))
        assertThrows<ActionExecutionException> { swapAction.apply(elements) }
    }

    @Test
    fun cancel() {
        val elements = LinkedList(listOf(1, 3, 2))
        swapAction.cancel(elements)
        assertEquals(elements, LinkedList(listOf(1, 2, 3)))
    }

    companion object {
        private const val firstIndex = 1
        private const val secondIndex = 2
        private val swapAction = SwapAction(firstIndex, secondIndex)
    }
}
