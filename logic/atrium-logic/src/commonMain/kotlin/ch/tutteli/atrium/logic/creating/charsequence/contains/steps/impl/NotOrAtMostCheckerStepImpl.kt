package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotOrAtMostCheckerStep

class NotOrAtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String,
    override val entryPointStepLogic: CharSequenceContains.EntryPointStepLogic<T, S>
) : NotOrAtMostCheckerStep<T, S>, CharSequenceContains.CheckerStepInternal<T, S> {

    override val checkers = listOf(
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, notOrAtMostCall)
    )
}
