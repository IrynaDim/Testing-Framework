package prestashop;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


public class MainPageTest extends BaseTest {

    @Test
    public void checkSubscribeText() {
        String emailSubscribeElement = mainPage.getEmailSubscribeText();
        assertTrue(softAssertion.compareObjects("Get our latest news and special sales", emailSubscribeElement));
    }

    @Test
    public void checkUnSubscribeText() {
        String unsubscribeElement = mainPage.getUnsubscribeText();
        assertTrue(softAssertion.compareObjects("You may unsubscribe at any moment. " +
                "For that purpose, please find our contact info in the legal notice.", unsubscribeElement));
    }

    @Test
    public void checkSubscribeButtonText_upperCase() {
        String text = mainPage.getSubscribeButtonText();
        assertTrue(softAssertion.compareObjects("SUBSCRIBE", text));
    }

    @Test
    public void checkLanguageSize() {
        assertTrue(softAssertion.compareObjects(mainPage.getLanguages().size(), 46));
    }

    @Test
    public void checkUkrainianLanguageExist() {
        assertTrue(softAssertion.compareObjects(mainPage.getLanguages().stream()
                .filter(l -> l.getText()
                        .equals("Українська"))
                .count(), 1L));
    }
}
