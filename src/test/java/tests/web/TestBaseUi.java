package tests.web;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;


public class TestBaseUi {

    @BeforeAll
    static void beforeAll(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;

}
}
