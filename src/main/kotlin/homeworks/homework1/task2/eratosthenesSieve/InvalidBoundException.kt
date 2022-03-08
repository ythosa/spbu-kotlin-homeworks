package homeworks.homework1.task2.eratosthenesSieve

class InvalidBoundException(bound: Int) :
    IllegalArgumentException("passed bound=$bound must be greater then 0")
