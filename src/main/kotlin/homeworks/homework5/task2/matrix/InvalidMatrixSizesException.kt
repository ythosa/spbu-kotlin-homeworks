package homeworks.homework5.task2.matrix

class InvalidMatrixSizesException(firstSize: Pair<Int, Int>, secondSize: Pair<Int, Int>) :
    Exception("invalid matrix sizes: first=$firstSize, second=$secondSize")
