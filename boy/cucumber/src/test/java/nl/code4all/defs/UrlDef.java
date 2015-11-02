package nl.code4all.defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Created by boy on 28-5-2015.
 */
public class UrlDef extends BaseDef {

    @Given("^we go the the url$")
    public void we_go_the_the_url() {
        selenium.loadPage("http://localhost:8080/spring-cucumber-web/welcome.html");
    }
}
