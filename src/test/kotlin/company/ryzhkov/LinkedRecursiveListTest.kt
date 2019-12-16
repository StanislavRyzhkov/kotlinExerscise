package company.ryzhkov

import company.ryzhkov.list.Empty
import company.ryzhkov.list.InnerList
import company.ryzhkov.list.LinkedRecursiveList
import org.junit.Assert
import org.junit.Test

class LinkedRecursiveListTest {


    @Test fun getLastElementTest() {
        var list: LinkedRecursiveList<Int> = Empty
        for (i in 0 until 10) {
            list = InnerList(i, list)
        }
        Assert.assertEquals(0, list[list.size - 1])
    }

    @Test fun sizeTest() {
        var list: LinkedRecursiveList<Int> = Empty
        for (i in 0 until 10) {
            list = InnerList(i, list)
        }
        Assert.assertEquals(10, list.size)
    }
}
