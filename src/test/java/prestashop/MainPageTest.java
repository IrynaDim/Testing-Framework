package prestashop;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import prestashop.util.TestListener;

@Slf4j
@Listeners(TestListener.class)
public class MainPageTest extends BaseTest {

    @Test
    public void checkUkrainianLanguageExist() {
        getMainPageAction()
                .goToHomePage()
                .assertThatLanguageIsPresent("Українська");
    }

    @Test
    public void checkLanguageSize() {
        getMainPageAction()
                .goToHomePage()
                .assertLanguageSize(46);
    }

    @Test
    public void checkArtClothesButton_emptyListOfArts() {
        getMainPageAction()
                .goToHomePage()
                .assertThatClothesHoverArtIsEmpty();
    }

    @Test
    public void checkUnSubscribeText() {
        getMainPageAction()
                .goToHomePage()
                .assertUnsubscribeText("You may unsubscribe at any moment. " +
                        "For that purpose, please find our contact info in the legal notice.");
    }

    @Test
    public void checkSubscribeText() {
        getMainPageAction()
                .goToHomePage()
                .assertEmailSubscribeText("Get our latest news and special sales");
    }

    @Test
    public void checkAccessoriesClothesButton_notEmptyListOfAccessories() {
        getMainPageAction()
                .goToHomePage()
                .assertThatClothesHoverAccessoriesIsNotEmpty();
    }

    @Test
    public void checkHoverClothesButton_notEmptyListOfClothes() {
        getMainPageAction()
                .goToHomePage()
                .assertThatClothesHoverIsNotEmpty();
    }

    @Test
    public void checkSubscribeButtonText_upperCase() {
        getMainPageAction()
                .goToHomePage()
                .assertSubscribeButtonText("SUBSCRIBE");
    }

    @Test
    public void checkPopularClothes_CurrencyNameAndPriceNotNull() {
        getMainPageAction()
                .goToHomePage()
                .assertPopularClothes(8);
    }
}
