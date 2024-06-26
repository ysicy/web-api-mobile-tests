package tests.web;


import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BigencPage;


import static com.codeborne.selenide.Selenide.open;

public class BigencUiTests extends TestBaseUi {
    BigencPage bigencPage = new BigencPage();
    @DisplayName("Проверка текста на странице авторизации")
    @Tag("Web")
    @Test
    void CheckAuthButtonTest(){

        bigencPage
                .openPage()
                .clickButtonProfile()
                .checkingText();

    }
    @DisplayName("Проверка перехода на статическую страницу")
    @Tag("Web")
    @Test
    void StaticPageTest(){
        bigencPage.openPage()
                .buttonClick()
                .searchingText();
    }
    @ValueSource(strings = {"Москва", "Россия", "Сочи"})
    @DisplayName("Проверка отправки значения в строку поиска")
    @Tag("Web")
    @ParameterizedTest
    void CheckingSearchTest (String SearchQuery){

       bigencPage.openPage()
               .setInputSearch(SearchQuery)
               .resultPageInput();
    }
    @ValueSource(strings = {"Биология", "Физика", "Математика"})
    @DisplayName("Проверка ввода и поиска значения на странице Каталога")
    @Tag("Web")
    @ParameterizedTest
    void OpenCatalogPageTest (String SearchQuery){
        bigencPage.openPage()
                .catalogPage()
                .setInputCatalog(SearchQuery)
                .dropDownList()
                .resultThematicPage();
    }
    @Disabled
    @DisplayName("Проверка невозможности отправки заявки без заполнения формы Стать автором")
    @Tag("Web")
    @Test
    void CheckDisableButtonTest (){
        open("https://bigenc.ru/p/author");
     bigencPage.notClickButton();

    }

}
