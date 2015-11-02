package nl.code4all.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class Selenium {

    public static final int TIMEOUT = 10; // seconds

    @Value("${browser:CHROME}")
    private String browser;

    public void loadPage(String url) {
        sleep();
        driver().get(url);
    }

    private void sleep() {
        try {
            Thread.sleep(0000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriver driver() {
        return BrowserDriver.getCurrentDriver(BrowserType.valueOf(browser));
    }

    public void click(final String id) {
        sleep();

        waitForElement(id);
        getElement(id).click();
    }

    private WebElement getElement(String id) {
        return driver().findElement(By.id(id));
    }

    public void waitForElement(final String id) {
        await().atMost(TIMEOUT, SECONDS).pollDelay(1, MILLISECONDS).pollInterval(100, MILLISECONDS)
                .until(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        return isPresent(id) && isVisible(id);
                    }
                });
    }

    private boolean isVisible(String id) {
        return getElement(id).isDisplayed();
    }

    public void waitForElementWithName(final String name) {
        await().atMost(TIMEOUT, SECONDS).pollDelay(1, MILLISECONDS).pollInterval(100, MILLISECONDS)
                .until(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        return isPresentWithName(name);
                    }

                });
    }

    private boolean isPresentWithName(String name) {
        return !driver().findElements(By.name(name)).isEmpty();
    }

    public boolean isPresent(String id) {
        return !driver().findElements(By.id(id)).isEmpty();
    }

    public String getText(String id) {
        waitForElement(id);
        if (!driver().findElements(By.xpath("//label[@for='" + id + "']")).isEmpty()) {
            return driver().findElement(By.xpath("//label[@for='" + id + "']")).getText();
        } else {
            return getElement(id).getText();
        }
    }

    public void setText(String id, String text) {
        waitForElement(id);
        getElement(id).sendKeys(text);
    }

    public void select(String id, String name) {
        waitForElement(id);
        new Select(driver().findElement(By.id(id))).selectByVisibleText(name);

    }

    public String getSelected(String id) {
        waitForElement(id);
        return new Select(getElement(id)).getFirstSelectedOption().getText();
    }

    public int getNumberOfItemsInList(String id) {
        waitForElement(id);
        return new Select(getElement(id)).getOptions().size();
    }

    public void clickOnElementWithName(String name) {
        waitForElementWithName(name);
        driver().findElement(By.name(name)).click();
    }

    public void waitForValue(final String id) {
        await().atMost(TIMEOUT, SECONDS).pollDelay(1, MILLISECONDS).pollInterval(100, MILLISECONDS)
                .until(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        return !getValue(id).isEmpty();
                    }

                });
    }

    public void waitForElementByCss(final String cssName) {

        await().atMost(TIMEOUT, SECONDS).pollDelay(1, MILLISECONDS).pollInterval(100, MILLISECONDS)
                .until(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        return !driver().findElements(By.cssSelector(cssName)).isEmpty();
                    }

                });
    }

    public String getValue(String id) {
        waitForElement(id);
        return getElement(id).getAttribute("value");
    }

    public int getNumberOfIndexElements(String id) {
        int numberOfIndexElements = 0;
        while (isPresent(id + numberOfIndexElements)) {
            numberOfIndexElements++;
        }
        return numberOfIndexElements;
    }

    public void runJavaScript(String method) {
        ((JavascriptExecutor) driver()).executeScript(method);
    }

}
