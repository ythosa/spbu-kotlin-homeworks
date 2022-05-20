package tests.retest1.task1.arithmetic

class IntArithmetic(private val value: Int) : ArithmeticAvailable<IntArithmetic> {
    override fun plus(other: IntArithmetic): IntArithmetic = IntArithmetic(value + other.value)

    override fun minus(other: IntArithmetic): IntArithmetic = IntArithmetic(value - other.value)

    override fun times(other: IntArithmetic): IntArithmetic = IntArithmetic(value * other.value)

    override fun isNull(): Boolean = value == 0

    override fun equals(other: Any?): Boolean = (other is IntArithmetic) && value == other.value

    override fun hashCode(): Int = value
}

fun Int.toArithmetic(): IntArithmetic = IntArithmetic(this)
