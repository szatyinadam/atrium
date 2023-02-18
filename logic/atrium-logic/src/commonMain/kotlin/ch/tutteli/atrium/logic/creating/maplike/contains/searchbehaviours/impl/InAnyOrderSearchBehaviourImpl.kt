package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionMapLikeExpectation

class InAnyOrderSearchBehaviourImpl : InAnyOrderSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionMapLikeExpectation.IN_ANY_ORDER, description)
}
