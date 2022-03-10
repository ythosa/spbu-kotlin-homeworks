package homeworks.homework1.task2.primeNumbers.eratosthenesSieve

class InvalidBoundException(bound: Int) :
    IllegalArgumentException("passed bound=$bound must be greater then 0")
