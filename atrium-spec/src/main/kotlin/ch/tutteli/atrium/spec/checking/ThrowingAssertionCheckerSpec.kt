package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.startsWith
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class ThrowingAssertionCheckerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IReporter) -> IAssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assertionVerb = AssertionVerb.VERB
    val reporterResponse = "hello"
    val reporter = mock<IReporter> {
        on { format(any<IAssertion>(), any<StringBuilder>()) }.thenAnswer {
            (it.arguments[1] as StringBuilder).append(reporterResponse)
        }
    }
    val testee = testeeFactory(reporter)
    val assertionWhichHolds = object : IAssertion {
        override fun holds() = true
    }
    val assertionWhichFails = object : IAssertion {
        override fun holds() = false
    }

    prefixedDescribe("fun ${testee::fail.name}") {
        it("throws an IllegalArgumentException if the given assertion holds") {
            verbs.checkException {
                testee.fail(assertionVerb, 1, assertionWhichHolds)
            }.toThrow<IllegalArgumentException>().and.message.startsWith("the given assertion should fail:")
        }

        it("throws an AssertionError with the message formatted by the reporter") {
            verbs.checkException {
                testee.fail(assertionVerb, "1", assertionWhichFails)
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }
    }

    prefixedDescribe("fun ${testee::check.name}") {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(
                assertionWhichHolds,
                assertionWhichHolds
            ))
        }

        mapOf(
            "first assertion fails" to listOf(assertionWhichFails, assertionWhichHolds, assertionWhichHolds),
            "middle assertion fails" to listOf(assertionWhichHolds, assertionWhichFails, assertionWhichHolds),
            "last assertion fails" to listOf(assertionWhichHolds, assertionWhichHolds, assertionWhichFails)

        ).forEach { assertionFails, assertions ->
            it("throws an AssertionError with the message formatted by the reporter if the $assertionFails") {
                verbs.checkException {
                    testee.check(assertionVerb, 1, assertions)
                }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
            }
        }
    }
})
