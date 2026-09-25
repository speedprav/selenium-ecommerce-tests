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
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Covers TC_04, TC_05 from the project report:
 * cart badge count on add, and item removal.
 */
public class CartTest {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test(description = "TC_04: Adding a product updates the cart badge count correctly")
    public void addingProductUpdatesCartBadge() {
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 0, "Cart should start empty");
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1, "Cart badge should show 1 item");
    }

    @Test(description = "TC_05: Removing a product from the cart updates the cart contents")
    public void removingProductUpdatesCart() {
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain 1 item before removal");

        cartPage.removeBackpack();
        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty after removal");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
