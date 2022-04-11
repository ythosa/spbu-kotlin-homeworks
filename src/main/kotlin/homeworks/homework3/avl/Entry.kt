package homeworks.homework3.avl

data class Entry<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V> {
    override fun setValue(newValue: V): V {
        val previousValue = value
        value = newValue

        return previousValue
    }
}
