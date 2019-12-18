package company.ryzhkov

import company.ryzhkov.monads.None
import company.ryzhkov.monads.Option
import company.ryzhkov.monads.Some
import org.junit.Assert
import org.junit.Test

class FunctorTest {

    @Test fun someTest() {
        val option: Option<Int> = Some(3)
        val res = option.map { "This is string: $it!" }
        Assert.assertTrue(res is Some)
        val n = res as Some
        Assert.assertEquals("This is string: 3!", n.value)
    }

    @Test fun noneTest() {
        val option: Option<Int> = None
        val res = option.map { "This is string: $it!" }
        Assert.assertTrue(res is None)
    }
}
