package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl.DefaultNotChecker

class NotCheckerStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    override val entryPointStepLogic: CharSequenceContains.EntryPointStepLogic<T, S>
) : NotCheckerStep<T, S>, CharSequenceContains.CheckerStepInternal<T, S> {

    @Suppress( /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2*/ "DEPRECATION")
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override val checkers = listOf(
        entryPointStepLogic.container.getImpl(NotChecker::class) { DefaultNotChecker() }
    )
}
