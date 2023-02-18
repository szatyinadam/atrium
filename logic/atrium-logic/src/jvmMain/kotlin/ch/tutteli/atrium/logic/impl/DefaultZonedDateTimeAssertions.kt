@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ZonedDateTimeAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.DayOfWeek
import java.time.ZonedDateTime


class DefaultZonedDateTimeAssertions : ZonedDateTimeAssertions {
    override fun year(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
