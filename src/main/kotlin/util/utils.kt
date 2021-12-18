package util

import java.io.File

fun readResourceFile(path: String): List<String> {
    val resource = object {}.javaClass.getResource("/$path")
    return File(resource.toURI()).readLines()
}

fun <T> Iterable<T>.replace(old: T, new: T) = map { if (it == old) new else it }
fun <T> Iterable<T>.product(other: Iterable<T>) = flatMap { a -> other.map { b -> a to b } }
fun <T> List<List<T>>.transpose(): List<List<T>> {
    return (0 until this[0].size).map { x ->
        (this.indices).map { y ->
            this[y][x]
        }
    }
}
