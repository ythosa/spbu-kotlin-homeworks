package homeworks.homework3

import homeworks.homework3.avl.avlTreeOf

infix fun String.example(function: () -> Unit) {
    println("/* Example of $this")
    function()
    println("*/")
}

@Suppress("SpreadOperator", "MagicNumber")
fun main() {
    "tree balancing" example {
        val tree = avlTreeOf(*Array(13) { Pair(it, 0) })
        println(tree)
    }

    "tree put" example {
        val tree = avlTreeOf(*Array(10) { Pair(it, 0) })
        println(tree)
        println("// Put [11:0]")
        tree[11] = 0
        println(tree)
    }

    "tree remove" example {
        val tree = avlTreeOf(*Array(10) { Pair(it, 0) })
        println(tree)
        println("// Remove [3, 0]")
        tree.remove(3)
        println(tree)
    }
}
