package company.ryzhkov.list

sealed class LinkedRecursiveList<out T> : AbstractList<T>() {

    override fun get(index: Int): T {
        if (index < 0) throw ArrayIndexOutOfBoundsException()
        if (index >= size) throw ArrayIndexOutOfBoundsException()
        val innerList = this as InnerList<T>
        if (index == 0) return innerList.head
        return innerList.tail[index - 1]
    }

    override val size: Int
        get() = when(val e = this) {
            is Empty -> 0
            is InnerList<T> -> 1 + e.tail.size
        }

}

object Empty : LinkedRecursiveList<Nothing>()

data class InnerList<T>(
    val head: T,
    val tail: LinkedRecursiveList<T>
) : LinkedRecursiveList<T>()
