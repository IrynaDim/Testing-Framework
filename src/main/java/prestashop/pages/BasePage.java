package prestashop.pages;

import org.openqa.selenium.support.PageFactory;
import prestashop.config.Driver;
import prestashop.util.SoftWaitUtil;
import prestashop.util.SoftWebElementAction;

public abstract class BasePage {

    protected SoftWebElementAction operation;
    protected SoftWaitUtil waitUtil;

    protected BasePage() {
        operation = new SoftWebElementAction();
        waitUtil = new SoftWaitUtil();
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }
}
