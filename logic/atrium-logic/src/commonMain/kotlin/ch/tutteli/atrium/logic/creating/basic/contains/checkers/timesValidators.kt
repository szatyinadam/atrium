package ch.tutteli.atrium.logic.creating.basic.contains.checkers

/**
 * Validates that times is not `1`; throws an [IllegalArgumentException] otherwise, pointing the user to use the given
 * [exactlyCall] instead of the given [atMostCall].
 * @throws IllegalArgumentException in case [times] is `1`
 */
fun validateAtMost(
    times: Int,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String
) {
    require(1 != times) {
        "use ${exactlyCall(times)} instead of ${atMostCall(times)}; " +
            "${atMostCall(times)} defines implicitly ${atLeastCall(1)} as well"
    }
}

/**
 * Validates that [atLeastTimes] is not equal to or greater than [butAtMostTimes]; throws an [IllegalArgumentException]
 * otherwise, pointing the user to use the given [exactlyCall] in case [atLeastTimes] equals [butAtMostTimes].
 *
 * @throws IllegalArgumentException in case [atLeastTimes] is equal or greater than [butAtMostTimes]
 */
fun validateButAtMost(
    atLeastTimes: Int,
    butAtMostTimes: Int,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String
) {
    require(atLeastTimes != butAtMostTimes) {
        "use ${exactlyCall(butAtMostTimes)} instead of ${atLeastButAtMostCall(butAtMostTimes, butAtMostTimes)}"
    }

    require(atLeastTimes < butAtMostTimes) {
        "specifying ${butAtMostCall(butAtMostTimes)} does not make sense if ${atLeastCall(atLeastTimes)} was used before"
    }
}
