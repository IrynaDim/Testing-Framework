package prestashop.config;

import static org.testng.AssertJUnit.assertEquals;

public class SoftAssertion {

    public Boolean compareObjects(Object expected, Object actual, String actualToString) {
        if (!expected.getClass().equals(actual.getClass())) {
            Factory.logger.get().fail("Objects are not equals!");
            return false;
        }
        try {
            assertEquals(expected, actual);
            Factory.logger.get().info("Object compare successful");
            return true;
        } catch (AssertionError e) {
            Factory.logger.get().fail("Objects are not the same. Actual is :" + actualToString);
            return false;
        }
    }
}
