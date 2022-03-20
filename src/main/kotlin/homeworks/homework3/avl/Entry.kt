package homeworks.homework3.avl

data class Entry<K, V>(override val key: K, override val value: V) : MutableMap.MutableEntry<K, V> {
    override fun setValue(newValue: V): V {
        TODO("Not yet implemented")
    }
}
