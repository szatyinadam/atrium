package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation.*

class ThrowableThrownFailureHandler<T : Throwable?, R> : SubjectChanger.FailureHandler<T, R> {

    override fun createAssertion(
        container: AssertionContainer<T>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<R>.() -> Unit>
    ): Assertion {
        val assertions = mutableListOf(descriptiveAssertion)
        maybeAssertionCreator.fold({ /* nothing to do */ }) { assertionCreator ->
            assertions.add(
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(container, None, assertionCreator)
                    .build()
            )
        }
        container.maybeSubject.fold(
            { /* nothing to do */ },
            {
                if (it != null) assertions.add(propertiesOfThrowable(it))
            }
        )

        return if (assertions.size == 1) {
            assertions[0]
        } else {
            assertionBuilder.invisibleGroup
                .withAssertions(assertions)
                .build()
        }
    }

    companion object {

        /**
         * Returns an [AssertionGroup] with an [ExplanatoryAssertionGroupType] containing properties
         * of the given [throwable].
         */
        fun propertiesOfThrowable(
            throwable: Throwable,
            explanation: Assertion = createExplanation(throwable)
        ): AssertionGroup =
            assertionBuilder.explanatoryGroup
                .withInformationType(withIndent = false)
                .withAssertions(
                    explanation,
                    createHints(throwable, secondStackFrameOfParent = null)
                )
                .build()

        private fun createExplanation(throwable: Throwable) =
            assertionBuilder.explanatory
                .withExplanation(
                    OCCURRED_EXCEPTION_PROPERTIES,
                    throwable::class.simpleName ?: throwable::class.fullName
                )
                .build()

        private fun createHints(
            throwable: Throwable,
            secondStackFrameOfParent: String?
        ): Assertion {
            val assertions = mutableListOf(
                createMessageHint(throwable),
                createStackTraceHint(throwable, secondStackFrameOfParent)
            )
            assertions.addAll(createAdditionalHints(throwable))
            createCauseHint(throwable)?.let { assertions.add(it) }

            return assertionBuilder.explanatoryGroup
                .withDefaultType
                .withAssertions(assertions)
                .build()
        }

        private fun createMessageHint(throwable: Throwable) =
            assertionBuilder.descriptive
                .holding
                .withDescriptionAndRepresentation(
                    OCCURRED_EXCEPTION_MESSAGE,
                    throwable.message
                )
                .build()

        private fun createStackTraceHint(
            throwable: Throwable,
            secondStackFrameOfParent: String?
        ): Assertion {
            val stackTrace = if (secondStackFrameOfParent != null) {
                throwable.stackBacktrace.takeWhile { it != secondStackFrameOfParent }
            } else {
                throwable.stackBacktrace
            }
            val assertions = stackTrace.map {
                assertionBuilder.explanatory.withExplanation(Text(it)).build()
            }

            return assertionBuilder.list
                .withDescriptionAndEmptyRepresentation(OCCURRED_EXCEPTION_STACKTRACE)
                .withAssertions(assertions)
                .build()
        }


        private fun createCauseHint(throwable: Throwable): AssertionGroup? =
            throwable.cause?.let { cause -> createChildHint(throwable, cause, OCCURRED_EXCEPTION_CAUSE) }

        /**
         * Creates a hint for a given [child] of the given [throwable] using the given [childDescription].
         */
        fun createChildHint(
            throwable: Throwable,
            child: Throwable,
            childDescription: Translatable
        ): AssertionGroup {
            val secondStackTrace = if (throwable.stackBacktrace.size > 1) throwable.stackBacktrace[1] else null
            return assertionBuilder.list
                .withDescriptionAndRepresentation(childDescription, child)
                .withAssertion(createHints(child, secondStackTrace))
                .build()
        }
    }
}

/**
 * Hook for platform specific additional hints.
 */
expect fun createAdditionalHints(throwable: Throwable): List<Assertion>
