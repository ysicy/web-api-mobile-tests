package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class BigencPage {

    /////Elements
    SelenideElement buttonProfile = $x("//div[@class='bre-header-profile']"),
    plashkaWithText = $x("//div[@class='bre-auth-disclaimer']"),
    aboutProjectButton = $x("//a[@href='/p/about-project']"),
    hasText = $x("//div[@class='bre-article-layout']"),
    inputSearch = $x("//nav[@class='bre-header-nav']/div[3]//input[@name='new-search']"),
    searchResult = $x("//div[@class='search-results']"),
    catalogForm = $x("//div[contains(@class,'_catalog')]"),
    inputSearchCatalog = $x("//div[contains(@class,'categories-list')]/div//input"),
    valueFromDropDown = $x("//div[contains(@class,'search-suggestions__wrapper')]//ul"),
    pageTitle = $x("//div[contains(@class,'bre-page')]"),
    sendButton = $x("//div[contains(@class,'bre-form-row')][5]//button");



    ////actions

    public BigencPage openPage(){
        open("https://bigenc.ru/");
        return this;

    }
    public BigencPage clickButtonProfile(){
        buttonProfile.click();
        return this;

    }
    public void checkingText (){
       plashkaWithText.shouldHave(text("Авторизуйтесь"));

    }
    public  BigencPage buttonClick (){
        aboutProjectButton.click();
       return this;
    }
    public void searchingText (){
        hasText.shouldHave(text("О портале"));
    }
    public BigencPage setInputSearch (String searchQuery){
        inputSearch.setValue(searchQuery).pressEnter();
        return this;
    }
    public void resultPageInput (){
        searchResult.should(visible);
    }
    public  BigencPage catalogPage (){
        catalogForm.click();
        return this;
    }
    public BigencPage setInputCatalog (){
        inputSearchCatalog.setValue("Биология").pressEnter();
        return this;
    }
    public BigencPage dropDownList (){
        valueFromDropDown.click();
        return this;
    }
    public void resultThematicPage (){
        pageTitle.shouldHave(text("Биология"));

    }
    public BigencPage notClickButton (){
        sendButton.shouldHave(visible);
        return this;
    }




}
