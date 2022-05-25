package tests.retest1.task1.vector

import tests.retest1.task1.arithmetic.ArithmeticAvailable

class Vector<T : ArithmeticAvailable<T>> {
    private val data: MutableList<T>
    private val size: Int

    constructor(vararg fromData: T) {
        if (fromData.isEmpty()) {
            throw InvalidVectorSizeException()
        }

        data = fromData.toMutableList()
        size = fromData.size
    }

    constructor(fromData: List<T>) {
        if (fromData.isEmpty()) {
            throw InvalidVectorSizeException()
        }

        data = fromData.toMutableList()
        size = fromData.size
    }

    operator fun plus(other: Vector<T>): Vector<T> {
        if (size != other.size) {
            throw InvalidVectorSizesException()
        }

        return Vector(List(size) { data[it] + other.data[it] })
    }

    operator fun minus(other: Vector<T>): Vector<T> {
        if (size != other.size) {
            throw InvalidVectorSizesException()
        }

        return Vector(List(size) { data[it] - other.data[it] })
    }

    operator fun times(other: Vector<T>): T {
        if (size != other.size) {
            throw InvalidVectorSizesException()
        }

        return data
            .mapIndexed { idx, value -> value * other.data[idx] }
            .reduce { sum, element -> sum + element }
    }

    fun isNull(): Boolean = data.all { it.isNull() }

    override operator fun equals(other: Any?): Boolean = (other is Vector<*>) && data == other.data

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + size

        return result
    }
}
