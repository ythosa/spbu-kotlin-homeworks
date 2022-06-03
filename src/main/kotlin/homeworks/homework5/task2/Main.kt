package homeworks.homework5.task2

import homeworks.homework5.task2.matrix.Matrix
import java.util.Scanner

fun readMatrix(scanner: Scanner, matrixName: String): Matrix {
    print("\uD83C\uDF4A Input $matrixName rows count: ")
    val rowsCount = scanner.nextInt()

    print("\uD83E\uDD6D Input $matrixName columns count: ")
    val columnsCount = scanner.nextInt()

    println("\uD83C\uDF67 Input matrix:")
    val matrixData = List(rowsCount) { MutableList(columnsCount) { 0 } }
    for (row in 0 until rowsCount) {
        for (column in 0 until columnsCount) {
            matrixData[row][column] = scanner.nextInt()
        }
    }

    return Matrix(matrixData)
}

fun main() {
    val scanner = Scanner(System.`in`)

    val firstMatrix = readMatrix(scanner, "first")
    val secondMatrix = readMatrix(scanner, "second")

    println("\uD83C\uDF70 Result: ")
    println(firstMatrix * secondMatrix)
}
