package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Covers TC_06, TC_07 from the project report:
 * checkout form validation, and successful order completion.
 */
public class CheckoutTest {

    private WebDriver driver;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.goToCart();
        checkoutPage = cartPage.goToCheckout();
    }

    @Test(description = "TC_06: Checkout form rejects submission when required fields are empty")
    public void checkoutRejectsEmptyForm() {
        checkoutPage.continueToOverview(); // no data entered
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error should be shown for empty form");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test(description = "TC_07: Valid checkout data completes the order and shows confirmation")
    public void validCheckoutCompletesOrder() {
        checkoutPage.fillInformation("Pravinkumar", "Choudhary", "390001");
        checkoutPage.continueToOverview();
        checkoutPage.finishOrder();

        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order should complete successfully");
        Assert.assertEquals(checkoutPage.getCompleteMessage(), "Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
