package homeworks.homework5.task2.matrix

fun generateMatrixWithSizes(rowsCount: Int, columnsCount: Int) = Matrix(
    List(rowsCount) { List(columnsCount) { it } }
)
