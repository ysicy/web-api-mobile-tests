package tests.web;


import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BigencPage;



import static com.codeborne.selenide.Selenide.open;

@Tag("ui")
public class BigencUiTests  extends  TestBaseUi{
    BigencPage bigencPage = new BigencPage();

    @DisplayName("Проверка текста на странице авторизации")
    @Test
    void CheckAuthButtonTest(){

        bigencPage
                .openPage()
                .clickButtonProfile()
                .checkingText();

    }

    @DisplayName("Проверка перехода на статическую страницу")
    @Test
    void StaticPageTest(){
        bigencPage.openPage()
                .buttonClick()
                .searchingText();
    }

    @ValueSource(strings = {"Москва", "Россия", "Сочи"})
    @DisplayName("Проверка отправки значения в строку поиска")
    @ParameterizedTest
    void CheckingSearchTest (String SearchQuery){

       bigencPage.openPage()
               .setInputSearch(SearchQuery)
               .resultPageInput();
    }

    @ValueSource(strings = {"Биология", "Физика", "Математика"})
    @DisplayName("Проверка ввода и поиска значения на странице Каталога")
    @ParameterizedTest
    void OpenCatalogPageTest (String SearchQuery){
        bigencPage.openPage()
                .catalogPage()
                .setInputCatalog(SearchQuery)
                .dropDownList()
                .resultThematicPage();
    }

    @DisplayName("Проверка невозможности отправки заявки без заполнения формы Стать автором")
    @Test

    void CheckDisableButtonTest (){
        open("https://bigenc.ru/p/author");
     bigencPage.notClickButton();

    }

    @Test
    public void TestingWithSelenoidTest(){
        Selenide.open("https://vk.com/");
    }
}
