package tests.web;


import com.codeborne.selenide.logevents.SelenideLogger;
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
    @Tag("Api")
    @Test
    void CheckAuthButtonTest(){

        bigencPage
                .openPage()
                .clickButtonProfile()
                .checkingText();

    }
    @DisplayName("Проверка перехода на статическую страницу")
    @Tag("Api")
    @Test
    void StaticPageTest(){
        bigencPage.openPage()
                .buttonClick()
                .searchingText();
    }
    @ValueSource(strings = {"Москва", "Россия", "Сочи"})
    @DisplayName("Проверка отправки значения в строку поиска")
    @Tag("Api")
    @ParameterizedTest
    void CheckingSearchTest (String SearchQuery){

       bigencPage.openPage()
               .setInputSearch(SearchQuery)
               .resultPageInput();
    }
    @ValueSource(strings = {"Биология", "Физика", "Математика"})
    @DisplayName("Проверка ввода и поиска значения на странице Каталога")
    @Tag("Api")
    @ParameterizedTest
    void OpenCatalogPageTest (String SearchQuery){
        bigencPage.openPage()
                .catalogPage()
                .setInputCatalog(SearchQuery)
                .dropDownList()
                .resultThematicPage();
    }
    @DisplayName("Проверка невозможности отправки заявки без заполнения формы Стать автором")
    @Tag("Api")
    @Test
    void CheckDisableButtonTest (){
        open("https://bigenc.ru/p/author");
     bigencPage.notClickButton();

    }

}
