@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.BasicAssertionGroup
import ch.tutteli.atrium.assertions.builders.BasicAssertionGroupFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class BasicAssertionGroupFinalStepImpl(
    override val groupType: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>
) : BasicAssertionGroupFinalStep {
    override fun build(): AssertionGroup = BasicAssertionGroup(groupType, description, representation, assertions)
}
