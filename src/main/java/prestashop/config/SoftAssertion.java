package prestashop.config;

import static org.testng.AssertJUnit.assertEquals;

public class SoftAssertion {

    public Boolean compareObjects(Object expected, Object equals) {
        try {
            assertEquals(expected, equals);
            Factory.logger.get().info("Object compare successful");
            return true;
        } catch (AssertionError e) {
            Factory.logger.get().fail("Object compare error");
            return false;
        }
    }
}
