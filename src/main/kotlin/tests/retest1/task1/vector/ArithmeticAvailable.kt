package tests.retest1.task1.vector

interface ArithmeticAvailable<T> {
  operator fun plus(other: T): T
  operator fun minus(other: T): T
  operator fun times(other: T): T
}
