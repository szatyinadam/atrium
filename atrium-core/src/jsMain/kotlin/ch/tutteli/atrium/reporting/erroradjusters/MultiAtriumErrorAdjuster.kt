package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual class MultiAtriumErrorAdjuster actual constructor(
    private val firstAdjuster: AtriumErrorAdjuster,
    private val secondAdjuster: AtriumErrorAdjuster,
    private val otherAdjusters: List<AtriumErrorAdjuster>
) : FilterAtriumErrorAdjuster(), AtriumErrorAdjuster {

    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        firstAdjuster.adjustStack(stackTrace)
            .let { secondAdjuster.adjustStack(it) }
            .let {
                otherAdjusters.fold(it) { filteredStack, adjuster ->
                    adjuster.adjustStack(filteredStack)
                }
            }
}
