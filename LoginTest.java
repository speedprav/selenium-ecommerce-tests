package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Covers TC_01, TC_02, TC_03 from the project report:
 * valid login, invalid password, locked-out user.
 */
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Comment out the next line to watch the browser during a live demo
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test(description = "TC_01: Valid login redirects to the Products page")
    public void validLoginRedirectsToInventory() {
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should load after valid login");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(description = "TC_02: Invalid password shows the correct error banner")
    public void invalidPasswordShowsError() {
        loginPage.loginAs("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be shown");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test(description = "TC_03: Locked-out user is blocked with the expected error message")
    public void lockedOutUserIsBlocked() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be shown for locked-out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
