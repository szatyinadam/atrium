package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

class InOrderOnlyGroupedSearchBehaviourImpl : InOrderOnlyGroupedSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionIterableLikeExpectation.IN_ORDER_ONLY_GROUPED, description)
}
