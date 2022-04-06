package homeworks.homework3

import homeworks.homework3.avl.avlTreeOf

fun main() {
    val tree = avlTreeOf(*((0..9).map { Pair(it, it) }).toTypedArray())
    print(tree)
}
