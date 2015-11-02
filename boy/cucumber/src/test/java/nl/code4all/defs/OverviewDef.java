package nl.code4all.defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class OverviewDef extends BaseDef {

    @Then("^we see lorem ipsum text$")
    public void we_see_lorem_ipsum_text() throws Throwable {
        String msg = selenium.getText("lorem-text");

        assertTrue(msg.contains("Lorem ipsum dolor sit amet"));
    }

    @When("^we click on about$")
    public void we_click_on_about() throws Throwable {
        selenium.click("link-about");
    }

    @Then("^the about page title is displayed$")
    public void the_about_page_title_is_displayed() throws Throwable {
        String title = selenium.getText("page-title");

        assertTrue(title.equals("About"));
    }
}
