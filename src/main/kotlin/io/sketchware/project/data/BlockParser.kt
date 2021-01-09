package io.sketchware.project.data

import io.sketchware.utils.serialize
import io.sketchware.utils.toJson
import java.util.regex.Matcher
import java.util.regex.Pattern

object BlockParser {
    private const val regex = "\\{.*?\\}$(?:\\{|\\s|\\Z)"
    val pattern: Pattern = Pattern.compile(regex, Pattern.MULTILINE)

    inline fun <reified T> parseAsArray(string: String): List<T> {
        val matcher: Matcher = pattern.matcher(string)
        val array = ArrayList<T>()
        while (matcher.find()) {
            array.add(matcher.group(0).serialize())
        }
        return array
    }

    inline fun <reified T> toSaveableValue(list: List<T>) {
        var output = ""
        list.forEach {
            output += "\n${it.toJson()}"
        }
    }

}