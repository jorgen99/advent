package util

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readResourceFile(path: String): List<String> {
    val resource = object {}.javaClass.getResource("/$path")
    return File(resource.toURI()).readLines()
}

fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5")
        .digest(toByteArray()))
        .toString(16)
