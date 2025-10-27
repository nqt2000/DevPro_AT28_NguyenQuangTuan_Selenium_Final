package actions.common;


import org.testng.Assert;

public class AssertUtils {
    // 1. Assert equals
    public static void assertEquals(String actualResult, String expectedResult, String... message) {
        Assert.assertEquals(actualResult, expectedResult);
    }
    // 2. Assert not equals

    // 3. Assert ignore case

    // 4. Assert true

    // 5. Assert false

    // 6. Assert contains

    public static void assertContains(String value, String valueContains, String... message) {
        Assert.assertTrue(value.contains(valueContains));
    }
}
