package tests.retest1.task1.vector

class Vector<T : ArithmeticAvailable<T>>(fromData: List<T>) {
  private val data: MutableList<T> = fromData.toMutableList()
  private val size = fromData.size

  operator fun plus(other: Vector<T>): Vector<T> {
    if (size != other.size) {
      throw InvalidVectorSizesException()
    }

    data.mapInPlaceIndexed { idx, value -> value + other.data[idx] }

    return this
  }

  operator fun minus(other: Vector<T>): Vector<T> {
    if (size != other.size) {
      throw InvalidVectorSizesException()
    }

    data.mapInPlaceIndexed { idx, value -> value - other.data[idx] }

    return this
  }

  operator fun times(other: Vector<T>): T {
    if (size != other.size) {
      throw InvalidVectorSizesException()
    }

    return data.mapIndexed { idx, value -> value * other.data[idx] }
      .reduce { sum, element -> sum + element }
  }
}
