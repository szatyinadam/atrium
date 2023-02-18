@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

abstract class AbstractTextObjectFormatter(
    private val translator: Translator
) : TextObjectFormatterCommon(translator) {
    /**
     * Returns a formatted version of the given [value].
     *
     * The following rules apply for the representation of an object:
     * - `null` is represented as [Text.NULL].[Text.string]
     * - [LazyRepresentation] is [evaluated][LazyRepresentation.eval] and then again [format]ted
     * - [Char] is put in apostrophes
     * - [Boolean] is represented with its [toString] representation
     * - [String] is put in quotes and its [Class.getName] is omitted
     * - [CharSequence] is put in quotes, but [KClass.qualifiedName] is used in contrast to [String]
     * - [Text] is represented as [Text.string]
     * - [Translatable] is represented as result of its translation (by [translator])
     * - [Class] is represented as "[Class.getSimpleName] ([Class.getName])"
     * - [KClass] is represented as "[KClass.simpleName] ([KClass.qualifiedName])" unless the [KClass.qualifiedName]
     *   differs from [Class.getName] in which case, "-- Class: [Class.getName]" is appended in addition
     * - [Enum] is represented as "[toString] ([Class.getName])
     * - [Throwable] is represented as "[Class.getName]"
     * - All other objects are represented as "[toString] ([Class.getName] <[System.identityHashCode]>)"
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    final override fun format(value: Any?): String = when (value) {
        is Class<*> -> format(value)
        else -> super.format(value)
    }

    private fun format(clazz: Class<*>) = "${clazz.simpleName} (${clazz.name})"

    final override fun format(kClass: KClass<*>): String {
        val kotlinClass = "${kClass.simpleName} (${kClass.qualifiedName})"
        return when {
            kClass.qualifiedName == kClass.java.name -> kotlinClass
            kClass.java.isPrimitive -> "$kotlinClass -- Class: ${kClass.java.simpleName}"
            else -> "$kotlinClass -- Class: ${kClass.java.name}"
        }
    }

    override fun identityHash(indent: String, any: Any): String = "$indent<${System.identityHashCode(any)}>"
}

/**
 * Formats an object by using its [toString] representation, its [Class.getName] and its [System.identityHashCode]
 * (in most cases).
 *
 * Some objects are treated differently, refer to [format] for details.
 *
 * The aim of representing more information than just [toString] is to avoid situations where an assert may fail
 * and [toString] does not distinguish two compared objects.
 * Consider the following error message "error, assert: 1 to be 1" would not be very helpful.
 * "error, assert: 1 (Int <123>) to be 1 (Double <456>)" on the other hand is helpful.
 *
 * @property translator The [Translator] used to translate [Translatable]s.
 *
 * @constructor Formats an object by using its [toString] representation, its [Class.getName] and its [System.identityHashCode]
 *   (in most cases).
 * @param translator The [Translator] used to translate [Translatable]s.
 */
actual class DefaultTextObjectFormatter actual constructor(
    private val translator: Translator
) : AbstractTextObjectFormatter(translator), TextObjectFormatter
