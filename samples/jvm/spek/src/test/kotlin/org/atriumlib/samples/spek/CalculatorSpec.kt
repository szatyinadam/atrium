package org.atriumlib.samples.spek

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.lang.IllegalArgumentException

object CalculatorSpec : Spek({
    describe("Calculator") {
        val calculator by memoized { Calculator() }

        it("1 + 2 = 3") {
            val sum = 3
            expect(calculator.add(1, 2)).toEqual(sum)
        }

        it("division sanity") {
            expect {
                // the below statement throws exception
                calculator.divide(12, 0)
            }.toThrow<IllegalArgumentException> { messageToContain("division by zero") }
        }

        it("defaults to fancy"){
            expect(calculator.isFancy).toEqual(true)
        }
    }
})

