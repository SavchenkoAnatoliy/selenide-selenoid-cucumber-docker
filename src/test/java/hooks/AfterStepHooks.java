package hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

/**
 * Hooks class for actions performed after each step in Cucumber scenarios.
 * This class takes screenshots after each step for debugging and reporting purposes.
 * It helps in capturing the state of the application after each step for better traceability.
 * The screenshots are useful for debugging and understanding the flow of the test execution.
 * This class enhances the test reporting by providing visual feedback on the state of the application at each step.
 */
public class AfterStepHooks {
    // Действия совершаемые после каждого шага
    @AfterStep
    public void takeScreenShotAfterStep(Scenario scenario) {
        Selenide.screenshot(System.currentTimeMillis() + "steps");
    }
}
