package prestashop.pages;

import prestashop.config.Operation;

public abstract class BasePage {

    protected Operation operation;

    BasePage() {
        operation = new Operation();
    }
}
