package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class CollectingExpectImpl<T>(
    maybeSubject: Option<T>,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), CollectingExpect<T> {
    private val assertions = mutableListOf<Assertion>()

    override fun getAssertions(): List<Assertion> = assertions.toList()

    override fun append(assertion: Assertion): Expect<T> =
        apply { assertions.add(assertion) }

    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allAssertions = mutableListOf<Assertion>()
        allAssertions.addAll(getAssertions())
        assertions.clear()

        assertionCreator(this)
        val newAssertions = getAssertions()

        assertions.clear()

        if (newAssertions.isNotEmpty()) {
            allAssertions.addAll(newAssertions)
        } else {
            allAssertions.add(assertionBuilder.descriptive
                .failing
                .withHelpOnFailure {
                    assertionBuilder.explanatoryGroup
                        .withDefaultType
                        .withAssertions(
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION)
                                .build(),
                            assertionBuilder.explanatory.withExplanation(ErrorMessages.HINT_AT_LEAST_ONE_EXPECTATION_DEFINED)
                                .build()
                        )
                        .build()
                }
                .showForAnyFailure
                .withDescriptionAndRepresentation(ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED, false)
                .build()
            )
        }
        allAssertions.forEach { append(it) }
        return this
    }

}
