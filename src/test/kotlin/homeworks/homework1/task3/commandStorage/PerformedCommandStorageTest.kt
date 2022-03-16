package homeworks.homework1.task3.commandStorage

import homeworks.homework1.task3.commandStorage.actions.PushBackAction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PerformedCommandStorageTest {
    private var commandStorage: PerformedCommandStorage? = null

    @BeforeEach
    fun initCommandStorage() {
        commandStorage = PerformedCommandStorage()
    }

    @Test
    fun apply() {
        commandStorage!!.apply(PushBackAction(1))
        assertEquals(commandStorage!!.getElements(), listOf(1))
    }

    @Test
    fun cancel() {
        commandStorage!!.apply(PushBackAction(1))
        commandStorage!!.cancel()
        assertEquals(commandStorage!!.getElements(), emptyList<Int>())
    }

    @Test
    fun `cancel error`() {
        assertThrows<ImpossibleCancelCommandException> { commandStorage!!.cancel() }
    }
}
