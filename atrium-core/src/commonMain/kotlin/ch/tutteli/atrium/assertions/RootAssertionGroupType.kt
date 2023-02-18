package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.Expect

/**
 * The [AssertionGroupType] for [AssertionGroup]s which are the root of all assertions.
 *
 * Or in other words, it is the [AssertionGroupType] for those [AssertionGroup]s which are created when
 * [Expect] is checking the added [Assertion]s.
 * Its [AssertionGroup.description] corresponds to the assertion verb used (e.g, `assert`).
 */
object RootAssertionGroupType : AssertionGroupType
