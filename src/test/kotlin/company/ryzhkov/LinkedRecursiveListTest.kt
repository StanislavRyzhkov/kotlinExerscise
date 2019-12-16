package company.ryzhkov

import company.ryzhkov.list.Empty
import company.ryzhkov.list.InnerList
import company.ryzhkov.list.LinkedRecursiveList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LinkedRecursiveListTest {
    var list: LinkedRecursiveList<Int> = Empty

    @Before fun init() {
        for (i in 0 until 10) {
            list = InnerList(i, list)
        }
    }

    @Test fun getLastElementTest() {
        Assert.assertEquals(0, list[list.size - 1])
    }

    @Test fun getElement0Test() {
        Assert.assertEquals(9, list[0])
    }

    @Test fun sizeTest() {
        Assert.assertEquals(10, list.size)
    }
}
