package homeworks.homework5.task2.matrix

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Matrix(private val data: List<List<Int>>) {
    private val rowsCount = data.size
    private val columnsCount = data[0].size

    constructor(vararg rows: List<Int>) : this(rows.asList())

    init {
        data.forEach {
            if (it.size != columnsCount)
                throw InvalidMatrixDataException("expected columns count=$columnsCount")
        }
    }

    operator fun times(other: Matrix): Matrix {
        if (columnsCount != other.rowsCount) {
            throw InvalidMatrixSizesException(rowsCount to columnsCount, other.rowsCount to other.columnsCount)
        }

        val resultRowsCount = rowsCount
        val resultColumnsCount = other.columnsCount

        val resultData: List<MutableList<Int>> = List(resultRowsCount) { MutableList(resultColumnsCount) { 0 } }

        runBlocking(Dispatchers.Default) {
            for (rowIndex in 0 until resultRowsCount) {
                for (columnIndex in 0 until resultColumnsCount) {
                    launch {
                        resultData[rowIndex][columnIndex] = dotProduct(data[rowIndex], other.getColumn(columnIndex))
                    }
                }
            }
        }

        return Matrix(resultData)
    }

    operator fun get(rowIndex: Int, columnIndex: Int) = data[rowIndex][columnIndex]

    operator fun get(rowIndex: Int) = data[rowIndex]

    private fun getColumn(columnIndex: Int) = data.map { it[columnIndex] }

    override fun toString(): String = data.joinToString("\n") { it.joinToString(" ") }

    private fun dotProduct(first: List<Int>, second: List<Int>): Int {
        require(first.size == second.size) { "lists must be with equal sizes" }

        return first.mapIndexed { index, element -> element * second[index] }.sum()
    }
}
