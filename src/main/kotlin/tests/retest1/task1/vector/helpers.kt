package tests.retest1.task1.vector

inline fun <T> MutableList<T>.mapInPlaceIndexed(mutator: (Int, T)->T) {
  this.forEachIndexed { idx, value ->
    mutator(idx, value).let { newValue ->
      if (newValue !== value) this[idx] = mutator(idx, value)
    }
  }
}
