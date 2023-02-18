package ch.tutteli.atrium.api.infix.en_GB.creating

import ch.tutteli.atrium.logic.utils.VarArgHelper

/**
 * Parameter object to express `String, vararg String` where they are treated as regex patterns.
 *
 * Use the function `regexPatterns("pattern", ...)` to create this representation.
 */
class RegexPatterns internal constructor(pattern: String, otherPatterns: Array<out String>) :
    VarArgHelper<String> {
    override val expected = pattern
    override val otherExpected = otherPatterns
}
