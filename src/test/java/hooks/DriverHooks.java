package hooks;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverHooks {
    /**
     * Настройка таймаута для взаимодействия с элементами перед запуском тестов
     */
    @Before
    public void setUpDriverBeforeScenario(){
        WebDriverManager.chromedriver().setup(); // Ensure correct ChromeDriver version
        Configuration.browser = "chrome"; // Set browser to Chrome
        Configuration.timeout = 60000;
    }
}
