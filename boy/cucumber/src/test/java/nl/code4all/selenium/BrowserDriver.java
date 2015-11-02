package nl.code4all.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;

public class BrowserDriver {

    private static WebDriver mDriver;

    public synchronized static WebDriver getCurrentDriver(BrowserType browserType) {
        if (mDriver == null) {
            try {
                switch (browserType) {
                    case CHROME:
                        mDriver = new ChromeDriver(createChromeService(), createChromeOptions());
                        break;
                }
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        }
        return mDriver;
    }

    private static ChromeDriverService createChromeService() {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        builder.usingDriverExecutable(new File(BrowserDriver.class.getResource("/chrome/chromedriver.exe").getFile()));
        builder.usingAnyFreePort();
        return builder.build();
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        return chromeOptions;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }

    public static void close() {
        try {
            getCurrentDriver(null).quit();
            mDriver = null;
        } catch (UnreachableBrowserException e) {
        }
    }
}
