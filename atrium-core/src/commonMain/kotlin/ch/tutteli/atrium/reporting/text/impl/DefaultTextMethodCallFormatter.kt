package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.text.TextMethodCallFormatter

/**
 * Responsible to format a method call for text output (e.g. to the console) where it represents arguments of a
 * method call by using their [Any.toString] representation with a few exceptions.
 *
 * The exceptions are:
 * - [CharSequence], is wrapped in quotes (`"`) and \r as well as \n are escaped.
 * - [Char] is wrapped in apostrophes (`'`)
 */
object DefaultTextMethodCallFormatter : TextMethodCallFormatter {
    override fun formatCall(methodName: String, arguments: Array<out Any?>): String =
        arguments.joinToString(", ", prefix = "$methodName(", postfix = ")") { formatArgument(it) }

    override fun formatArgument(argument: Any?): String = when (argument) {
        null -> Text.NULL.string
        is CharSequence -> "\"$argument\"".replace("\r", "\\r").replace("\n", "\\n")
        is Char -> "'$argument'"
        else -> argument.toString()
    }
}
