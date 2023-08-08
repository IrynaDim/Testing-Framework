package prestashop.pages;

import prestashop.config.SoftWebElementAction;

public abstract class BasePage {

    protected SoftWebElementAction operation;

    BasePage() {
        operation = new SoftWebElementAction();
    }
}
