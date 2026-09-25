package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeBackpack() {
        click(removeBackpack);
    }

    public CheckoutPage goToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public List<String> getItemNames() {
        return driver.findElements(By.className("inventory_item_name"))
                .stream().map(e -> e.getText()).toList();
    }
}
