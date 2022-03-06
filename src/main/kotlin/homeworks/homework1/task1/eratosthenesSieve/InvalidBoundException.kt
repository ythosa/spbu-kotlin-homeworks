package homeworks.homework1.task1.eratosthenesSieve

class InvalidBoundException(bound: Int) :
    IllegalArgumentException("passed bound=$bound must be greater then 0")
