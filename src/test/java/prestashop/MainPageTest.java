package prestashop;

import org.testng.annotations.Test;

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
                .assertLanguageSize();
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
                .assertUnsubscribeText();
    }

    @Test
    public void checkSubscribeText() {
        getMainPageAction()
                .goToHomePage()
                .assertEmailSubscribeText();
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
                .assertSubscribeButtonText();
    }

    @Test
    public void checkPopularClothes_CurrencyNameAndPriceNotNull() {
        getMainPageAction()
                .goToHomePage()
                .assertPopularClothes();
    }
}
