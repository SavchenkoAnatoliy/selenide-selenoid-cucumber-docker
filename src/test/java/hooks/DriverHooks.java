package hooks;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

/**
 * Hooks class for setting up and tearing down the WebDriver before and after each test scenario.
 * This class configures Selenide to use Selenoid as the remote WebDriver.
 * It handles the initialization and cleanup of the WebDriver for each test.
 * The class ensures that the WebDriver is properly configured for remote execution using Selenoid.
 * It is responsible for managing the lifecycle of the WebDriver, ensuring that it is set up correctly before each test and cleaned up afterward.
 */
public class DriverHooks {

    /**
     * Sets up the WebDriver configuration before each test scenario.
     * Configures Selenide to use Selenoid as the remote WebDriver.
     */
    @Before
    public void setUp() {
        // URL of your local or remote Selenoid
        Configuration.remote = "http://172.17.0.4:4444/wd/hub"; // Using container's IP address
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 40000;
        Configuration.headless = false;
        
        // Connection settings
        Configuration.remoteConnectionTimeout = 60000;
        Configuration.remoteReadTimeout = 60000;
        Configuration.reportsFolder = "build/reports/tests";
        
        // Additional Selenide settings
        Configuration.driverManagerEnabled = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        
        // Additional Chrome options for better compatibility
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--allow-running-insecure-content");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.browserCapabilities = capabilities;
    }

    /**
     * Tears down the WebDriver after each test scenario.
     * Closes the WebDriver to free up resources.
     */
    @After
    public void tearDown() {
        // Close the WebDriver after each scenario
        WebDriverRunner.closeWebDriver();
    }

    // Действия совершаемые после каждого шага
    @AfterStep
    public void takeScreenShotAfterStep(Scenario scenario) {
        Selenide.screenshot(System.currentTimeMillis() + "steps");
    }

}