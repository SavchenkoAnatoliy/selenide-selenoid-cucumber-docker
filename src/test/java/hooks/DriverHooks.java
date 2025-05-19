package hooks;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class DriverHooks {
    /**
     * Настройка таймаута для взаимодействия с элементами перед запуском тестов
     */
//    @BeforeEach
//    public void setUpDriverBeforeScenario() throws MalformedURLException {
//        String selenoidURL = "http://127.0.0.1:4444/wd/hub";
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//
//// Кастомные опции для Selenoid
//        MutableCapabilities selenoidOptions = new MutableCapabilities();
//        selenoidOptions.setCapability("enableVNC", true);
//        selenoidOptions.setCapability("enableVideo", true);
//        selenoidOptions.setCapability("videoName", "test-video.mp4");
//        selenoidOptions.setCapability("screenResolution", "1920x1080x24");
//
//// Вложить selenoid options в chrome options
//        chromeOptions.setCapability("selenoid:options", selenoidOptions);
//
//// Настроить версию браузера (чтобы не пытался запускать chrome_75, которого нет)
//        chromeOptions.setBrowserVersion("105.0"); // Укажи реально доступную версию браузера в selenoid
//
//        WebDriver driver = new RemoteWebDriver(new URL(selenoidURL), chromeOptions);
//        driver.manage().window().setSize(new Dimension(1920, 1024));
//        WebDriverRunner.setWebDriver(driver);
//    }

    @Before
    public void setUp() {
        // URL of your local or remote Selenoid
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.headless = false; // Needed to view VNC session

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true); // allow remote viewing
        capabilities.setCapability("enableVideo", false); // enable if you have video recording in Selenoid
        capabilities.setCapability("screenResolution", "1920x1080x24");

        Configuration.browserCapabilities = capabilities;
    }

    @After
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

}





//    @BeforeEach
//    public void initDriver() throws IOException {
//        final String url = "http://192.168.90.245:4444/wd/hub";
//        WebDriver driver = new RemoteWebDriver(new URL(url), DesiredCapabilities.chrome());
//        driver.manage().window().setSize(new Dimension(1920,1024));
//        WebDriverRunner.setWebDriver(driver);
//    }

