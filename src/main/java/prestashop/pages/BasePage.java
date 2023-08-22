package prestashop.pages;

import prestashop.util.SoftWaitUtil;
import prestashop.util.SoftWebElementAction;

public abstract class BasePage {

    protected SoftWebElementAction operation;
    protected SoftWaitUtil waitUtil;

    protected BasePage() {
        operation = new SoftWebElementAction();
        waitUtil = new SoftWaitUtil();
    }
}
