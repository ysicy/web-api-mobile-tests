package tests.web;


import org.junit.jupiter.api.Test;
import pages.BigencPage;



import static com.codeborne.selenide.Selenide.open;

public class BigencUiTests extends TestBaseUi {

    BigencPage bigencPage = new BigencPage();

    @Test
    void BigencCheckAuthButtonTest(){
        bigencPage
                .openPage()
                .clickButtonProfile()
                .checkingText();

    }
    @Test
    void StaticPageTest(){
        bigencPage.openPage()
                .buttonClick()
                .searchingText();
    }
    @Test
    void CheckingSearchTest (){
       bigencPage.openPage()
               .setInputSearch()
               .resultPageInput();
    }

    @Test
    void OpenCatalogPageTest (){
        bigencPage.openPage()
                .catalogPage()
                .setInputCatalog()
                .dropDownList()
                .resultThematicPage();
    }

    @Test
    void CheckDisableButtonTest (){
        open("https://bigenc.ru/p/author");
     bigencPage.notClickButton();

    }

}
