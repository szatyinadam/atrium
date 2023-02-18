package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.kbox.isNotNullAndNotBlank

actual val Throwable.stackBacktrace: List<String>
    get() {
        val nullableStack: String? = asDynamic().stack as? String
        return if (nullableStack.isNotNullAndNotBlank()) {
            //TODO !! no longer necessary with Kotlin 1.3 and a newer KBox Version
            splitStackLines(nullableStack!!)
        } else {
            listOf("Could not populate the stackBacktrace, please file a bug report at $BUG_REPORT_URL")
        }
    }

private fun splitStackLines(stack: String): List<String> {
    val searchWord = "  at "
    val firstFrame = stack.indexOf(searchWord)
    return stack.substring(firstFrame + searchWord.length)
        .splitToSequence('\n')
        //TODO remove once https://youtrack.jetbrains.com/issue/KT-27920 is fixed
        .dropWhile {
            //kotlin 1.6
            it.contains("packages_imported/kotlin/") ||
                it.contains("packages_imported\\kotlin\\") ||
                // kotlin 1.3
                it.contains("_init_0")
        }
        .map { it.substringAfter(searchWord) }
        .toList()
}
