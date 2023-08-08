package prestashop.pages;

import org.openqa.selenium.interactions.Actions;
import prestashop.config.Factory;
import prestashop.config.SoftWebElementAction;

public abstract class BasePage {

    protected SoftWebElementAction operation;

    BasePage() {
        operation = new SoftWebElementAction();
    }
}
