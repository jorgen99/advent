package util

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readResourceFile(path: String): List<String> {
    val resource = object {}.javaClass.getResource("/$path")
    return File(resource.toURI()).readLines()
}

fun <T> Iterable<T>.replace(old: T, new: T) = map { if (it == old) new else it }
fun <T> Iterable<T>.product(other: Iterable<T>) = flatMap { a -> other.map { b -> a to b } }
