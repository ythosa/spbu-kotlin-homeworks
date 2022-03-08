package homeworks.homework1.task2

import homeworks.homework1.task2.eratosthenesSieve.EratosthenesSieveImperative
import homeworks.homework1.task2.eratosthenesSieve.InvalidBoundException

fun main() {
    print("Hello username! I can print all primes less than or equal to the n (boundary)!\nPlease enter the boundary: ")
    val boundary = readln().toIntOrNull()
    if (boundary == null) {
        println("Oops... boundary must be integer")

        return
    }

    val eratosthenesSieve = EratosthenesSieveImperative()
    try {
        val primes = eratosthenesSieve.getPrimesUpToTheBoundary(boundary)
        if (primes.isEmpty()) {
            println("Oops... There was not a single prime number under the boundary=$boundary")
        } else {
            println("Here is your primes: ${primes.joinToString(", ")}")
        }
    } catch (e: InvalidBoundException) {
        println("Oops... ${e.localizedMessage}")
    }
}
