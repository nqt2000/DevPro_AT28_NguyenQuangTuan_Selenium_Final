package actions.common;

import org.testng.Assert;

public class AssertUtils {

    // 1️⃣ Assert equals
    public static void assertEquals(String actualResult, String expectedResult, String... message) {
        if (message.length > 0) {
            Assert.assertEquals(actualResult, expectedResult, message[0]);
        } else {
            Assert.assertEquals(actualResult, expectedResult);
        }
    }

    // 2️⃣ Assert not equals
    public static void assertNotEquals(String actualResult, String expectedResult, String... message) {
        if (message.length > 0) {
            Assert.assertNotEquals(actualResult, expectedResult, message[0]);
        } else {
            Assert.assertNotEquals(actualResult, expectedResult);
        }
    }

    // 3️⃣ Assert equals ignore case
    public static void assertEqualsIgnoreCase(String actualResult, String expectedResult, String... message) {
        boolean result = actualResult.equalsIgnoreCase(expectedResult);
        if (message.length > 0) {
            Assert.assertTrue(result, message[0]);
        } else {
            Assert.assertTrue(result, String.format("Expected (ignore case): %s but found %s", expectedResult, actualResult));
        }
    }

    // 4️⃣ Assert true
    public static void assertTrue(boolean condition, String... message) {
        if (message.length > 0) {
            Assert.assertTrue(condition, message[0]);
        } else {
            Assert.assertTrue(condition);
        }
    }

    // 5️⃣ Assert false
    public static void assertFalse(boolean condition, String... message) {
        if (message.length > 0) {
            Assert.assertFalse(condition, message[0]);
        } else {
            Assert.assertFalse(condition);
        }
    }

    // 6️⃣ Assert contains
    public static void assertContains(String value, String valueContains, String... message) {
        boolean result = value.contains(valueContains);
        if (message.length > 0) {
            Assert.assertTrue(result, message[0]);
        } else {
            Assert.assertTrue(result, String.format("Expected value to contain '%s' but found '%s'", valueContains, value));
        }
    }
}
