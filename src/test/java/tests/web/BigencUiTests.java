package tests.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class BigencUiTests extends TestBaseUi {

    @Test
    void BigencCheckAuthButtonTest(){
        Selenide.open("https://bigenc.ru/");
        $x("//div[@class='bre-header-profile']").click();
        $x("//div[@class='bre-auth-disclaimer']").shouldHave(Condition.text("Авторизуйтесь"));
    }

    @Test
    void StaticPageTest(){
        open("https://bigenc.ru/");
        $x("//a[@href='/p/about-project']").click();
        $x("//div[@class='bre-article-layout']").shouldHave(Condition.text("О портале"));
    }

    @Test
    void CheckingSearchTest (){
        open("https://bigenc.ru/");
        $x("//nav[@class='bre-header-nav']/div[3]//input[@name='new-search']").setValue("тест")
                .pressEnter();
        $x("//div[@class='search-results']").shouldHave(Condition.text("тест"));

    }

    @Test
    void OpenCatalogPageTest (){

        open("https://bigenc.ru/");
        $x("//div[contains(@class,'_catalog')]").click();
        $x("//div[contains(@class,'categories-list')]/div//input").setValue("Биология").pressEnter();
        $x("//div[contains(@class,'search-suggestions__wrapper')]//ul").click();
        $x("//div[contains(@class,'bre-page')]").shouldHave(Condition.text("Биология"));
    }

    @Test
    void CheckDisableButtonTest (){
        open("https://bigenc.ru/");
        $x("//div[contains(@class,'bre-form-row')][5]//button")
                .shouldHave(Condition.not(Condition.visible));
    }

}
