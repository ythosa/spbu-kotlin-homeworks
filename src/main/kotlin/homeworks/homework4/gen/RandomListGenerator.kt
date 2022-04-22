package homeworks.homework4.gen

import kotlin.random.Random

class RandomListGenerator(
    private val minValue: Int,
    private val maxValue: Int,
    private val elementCount: Int
) {
    private constructor(builder: Builder) : this(builder.minValue, builder.maxValue, builder.elementsCount)

    fun generate(): List<Int> = List(elementCount) { Random.nextInt(minValue, maxValue) }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var minValue = 0
        var maxValue = 0
        var elementsCount = 0
            set(value) {
                if (value < 0)
                    throw IllegalArgumentException("the number of elements must be greater than or equal to 0")
                field = value
            }

        fun build() = RandomListGenerator(this)
    }
}
