package homeworks.homework4.gen

import kotlin.random.Random

class RandomListGenerator(
    private val minValue: Int,
    private val maxValue: Int,
    private val elementCount: Int
) {
    private constructor(builder: Builder) : this(builder.minValue, builder.maxValue, builder.elementCount)

    fun generate(): List<Int> = List(elementCount) { Random.nextInt(minValue, maxValue) }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var minValue = 0
        var maxValue = 0
        var elementCount = 0

        fun build() = RandomListGenerator(this)
    }
}
