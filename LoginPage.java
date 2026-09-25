package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.saucedemo.com");
    }

    public InventoryPage loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isVisible(errorMessage);
    }
}
