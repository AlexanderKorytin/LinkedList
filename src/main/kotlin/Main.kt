import java.lang.IndexOutOfBoundsException

fun main() {
    val list = LinkedList<String>()
    list.add(0, "0")
    list.add("1")
    list.add("2")
    list.add(2, "1.5")
    list.add("3")
    list.add(0, "new")
    println("list:")
    for (i in list) {
        println(i)
    }
    println("size - ${list.size()}")
    println(list.get(3))
    println(list.contain("3"))
    println(list.contain("2"))
    println(list.contain("y"))
    println("del")
    println(list.remove(0))
    println("list:")
    for (i in list) {
        println(i)
    }
    println("del")
    println(list.remove(1))
    println("list:")
    for (i in list) {
        println(i)
    }
    println("clear")
    list.clear()
    println("size - ${list.size()}")
}

class LinkedList<T> : Iterable<T> {

    private var size = 0
    private var head: Item<T>? = null
    private var last: Item<T>? = null

    class Item<T>(var value: T, var next: Item<T>?) {
        override fun toString(): String {
            return value.toString()
        }
    }

    fun add(value: T) {
        val newItem = Item(value, null)
        if (head == null) {
            head = newItem
            last = head
        } else {
            last?.next = newItem
            last = newItem
        }
        size++
    }

    fun remove(index: Int): T {
        val current = getItem(index)
        val removed = current!!.value
        if (size == 1) {
            clear()
        }
        if (index == 0) {
            head = current.next
            size--
        } else {
            val prev = getItem(index - 1)
            prev?.next = current.next
            size--
        }
        return removed
    }

    fun contain(item: T): Boolean {
        var current = head
        for (i in 0 until size) {
            if (current?.value === item) return true else current = current?.next
        }
        return false
    }

    fun size(): Int {
        return size
    }

    fun clear() {
        head = null
        last = null
        size = 0
    }

    fun add(index: Int, value: T) {
        val newItem = Item(value, null)
        if (size == 0) {
            head = newItem
            last = head
        } else if(index != 0){
            val current = getItem(index)
            val prev = getItem(index - 1)
            newItem.next = current
            prev?.next = newItem
        } else {
            newItem.next = head
            head = newItem
        }
        size++
    }

    fun get(index: Int) = getItem(index)?.value

    private fun getItem(index: Int): Item<T>? {
        if (index >= size || index < 0) throw IndexOutOfBoundsException("Size!!!")
        var current = head
        for (i in 0 until index) {
            current = current?.next
        }
        return current
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var current = head
            override fun hasNext(): Boolean = current != null
            override fun next(): T {
                val value: T = current!!.value
                current = current?.next
                return value
            }
        }
    }
}
