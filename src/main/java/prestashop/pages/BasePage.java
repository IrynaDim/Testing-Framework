package prestashop.pages;

import prestashop.util.SoftWebElementAction;

public abstract class BasePage {

    protected SoftWebElementAction operation;

    BasePage() {
        operation = new SoftWebElementAction();
    }
}
