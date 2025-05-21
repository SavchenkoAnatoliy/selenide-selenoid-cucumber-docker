package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Step definitions for the Klavogonki game automation.
 * This class contains methods to interact with the Klavogonki game using Selenide.
 * It handles opening the website, starting the game, waiting for the game to start, playing the game, and verifying the results.
 * The class uses Selenide for browser automation and assertions for result verification.
 * It is designed to automate the interaction with the Klavogonki game, ensuring that the game is played correctly and the results are verified.
 * The class enhances the test automation by providing a clear and structured way to interact with the game, making it easier to maintain and extend the tests.
 */
public class KlavogonkiStep {
    private final SelenideElement closeWindowButton = $x("//input[@value='Закрыть']");
    private final SelenideElement startGameButton = $x("//a[@id='host_start']");
    private final SelenideElement highlightWord = $x("//span[@id='typefocus']");
    private final SelenideElement inputField = $x("//input[@id='inputtext']");
    private final SelenideElement afterFocusWord = $x("//span[@id='afterfocus']");
    private final SelenideElement resultText = $x("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']//div[2]/span/span");

    private String getCurrentWord(){
        // Replace Latin characters with Cyrillic equivalents for proper word matching
        return highlightWord.getText().replaceAll("c", "с").replaceAll("o", "о");
    }

    @Given("Открываем сайт {string}")
    public void openWebSite(String url) {
        // Open the specified URL in the browser
        Selenide.open(url);
    }

    @When("Начинаем игру")
    public void startGame() {
        // Close the initial window and start the game if the start button is displayed
        closeWindowButton.click();
        if(startGameButton.isDisplayed()){
            startGameButton.click();
        }
    }

    @And("Ждем начало игры")
    public void waitForStartGame() {
        // Wait for the game to start by clicking the highlighted word
        highlightWord.click();
    }

    @And("Вводим подсвеченное слово в цикле")
    public void playGame() {
        // Continuously type the highlighted word until the game ends (indicated by a period)
        while (true){
            String currentWord = getCurrentWord();
            String afterFocusSymbol = afterFocusWord.getText();
            inputField.sendKeys(currentWord);
            if(afterFocusSymbol.equals(".")){
                inputField.sendKeys(".");
                break;
            }
            inputField.sendKeys(Keys.SPACE);
        }
    }

    @Then("Фиксируем что игра завершена и символов в минуту больше чем {int}")
    public void endGame(int minValue) {
        // Verify that the game is completed and the characters per minute exceed the minimum value
        String result = resultText.getText();
        int resultNumber = Integer.parseInt(result);
        System.out.println("Количество знаков в минуту: " + resultNumber);
        Assertions.assertTrue(resultNumber > minValue, "Актуальный результат был: " + resultNumber);
    }
}
