package ru.otus.homework

import kotlin.random.Random

fun main() {
    val list = createList(10)
    println("Source list:")
    println(list)

    println("Collection transformation:")
    println(list.collectionTransform())

    println("Sequence transformation:")
    println(list.sequenceTransformation())
}

fun List<RandomNumber>.collectionTransform(take: Int = 5) =
    filter { random -> random.filter { it >= 0 } }
        .map { it.stringify() }
        .take(take)
        .toSet()

fun List<RandomNumber>.sequenceTransformation(take: Int = 5) =
    asSequence()
        .filter { random -> random.filter { it >= 0 } }
        .map { it.stringify() }
        .take(take)
        .log()
        .toSet()

fun createList(length: Int = 10): List<RandomNumber> {
    return (1..length).map { RandomNumber() }
}

class LogSequence<T>(private val parent: Sequence<T>) : Sequence<T> {
    override fun iterator() = object : Iterator<T> {
        private val pi = parent.iterator()
        override fun hasNext(): Boolean = pi.hasNext()
        override fun next(): T = pi.next().also { println("Logging: $it") }
    }
}

fun <T> Sequence<T>.log(): Sequence<T> = LogSequence(this)

class RandomNumber {
    val value: Int = Random.nextInt(-10, 10)

    init {
        println("Created: $value")
    }

    fun filter(block: (Int) -> Boolean): Boolean {
        println("Filtering: $value")
        return block(value)
    }

    fun stringify(): String {
        println("Stringifying: $value")
        return toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RandomNumber

        return value == other.value
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "($value)"
    }
}