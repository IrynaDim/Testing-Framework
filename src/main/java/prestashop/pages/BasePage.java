package prestashop.pages;

import org.testng.asserts.SoftAssert;
import prestashop.config.Operation;
import prestashop.config.SoftWebElementAction;

public abstract class BasePage {

    protected Operation op;
    protected SoftAssert softAssert;
    protected SoftWebElementAction softWebElementAction;

    BasePage() {
        op = new Operation();
        softAssert = new SoftAssert();
        softWebElementAction = new SoftWebElementAction();
    }
}
