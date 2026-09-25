package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // Step One - information form
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    // Step Two - overview
    private final By finishButton = By.id("finish");

    // Complete
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void continueToOverview() {
        click(continueButton);
    }

    public boolean isErrorDisplayed() {
        return isVisible(errorMessage);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }

    public void finishOrder() {
        click(finishButton);
    }

    public boolean isOrderComplete() {
        return isVisible(completeHeader);
    }

    public String getCompleteMessage() {
        return textOf(completeHeader);
    }
}
