package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(inventoryContainer);
    }

    public void addBackpackToCart() {
        click(addToCartBackpack);
    }

    public void removeBackpackFromCart() {
        click(removeBackpack);
    }

    public int getCartBadgeCount() {
        if (!isVisible(cartBadge)) {
            return 0;
        }
        return Integer.parseInt(textOf(cartBadge));
    }

    public void sortBy(String visibleOptionText) {
        Select select = new Select(waitVisible(sortDropdown));
        select.selectByVisibleText(visibleOptionText);
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
