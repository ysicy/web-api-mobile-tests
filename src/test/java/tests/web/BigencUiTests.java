package tests.web;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BigencPage;


import static com.codeborne.selenide.Selenide.open;

public class BigencUiTests extends TestBaseUi {

    BigencPage bigencPage = new BigencPage();

    @DisplayName("Проверка текста на странице авторизации")
    @Tags({@Tag("Smoke"), @Tag("Web")})
    @Test
    void BigencCheckAuthButtonTest(){
        bigencPage
                .openPage()
                .clickButtonProfile()
                .checkingText();

    }
    @DisplayName("Проверка перехода на статическую страницу")
    @Tags({@Tag("Smoke"), @Tag("Web")})
    @Test
    void StaticPageTest(){
        bigencPage.openPage()
                .buttonClick()
                .searchingText();
    }
    @ValueSource(strings = {"Москва", "Россия", "Сочи"})
    @DisplayName("Проверка отправки значения в строку поиска")
    @Tags({@Tag("Smoke"), @Tag("Web")})
    @ParameterizedTest
    void CheckingSearchTest (String SearchQuery){
       bigencPage.openPage()
               .setInputSearch(SearchQuery)
               .resultPageInput();
    }
    @DisplayName("Проверка ввода и поиска значения на странице Каталога")
    @Tags({@Tag("Smoke"), @Tag("Web")})
    @Test
    void OpenCatalogPageTest (){
        bigencPage.openPage()
                .catalogPage()
                .setInputCatalog()
                .dropDownList()
                .resultThematicPage();
    }
    @DisplayName("Проверка невозможности отправки заявки без заполнения формы Стать автором")
    @Tags({@Tag("Smoke"), @Tag("Web")})
    @Test
    void CheckDisableButtonTest (){
        open("https://bigenc.ru/p/author");
     bigencPage.notClickButton();

    }

}
