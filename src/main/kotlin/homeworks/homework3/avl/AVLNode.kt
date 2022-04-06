package homeworks.homework3.avl

import java.lang.Integer.max

typealias Visitor<K, V> = (Entry<K, V>) -> Unit

class AVLNode<K : Comparable<K>, V>(var key: K, var value: V) {
    var height = 0
    private set

    var leftChild: AVLNode<K, V>? = null
    var rightChild: AVLNode<K, V>? = null

    private val leftHeight: Int
        get() = leftChild?.height ?: -1
    private val rightHeight: Int
        get() = rightChild?.height ?: -1

    val balanceFactor: Int
        get() = leftHeight - rightHeight

    val minChild: AVLNode<K, V>
        get() = leftChild?.minChild ?: this

    fun updateHeight() = this.apply { height = max(leftHeight, rightHeight) + 1 }

    fun traverse(visit: Visitor<K, V>) {
        visit(Entry(key, value))
        leftChild?.traverse(visit)
        rightChild?.traverse(visit)
    }

    override fun toString() = "[$key : $value]"
}
