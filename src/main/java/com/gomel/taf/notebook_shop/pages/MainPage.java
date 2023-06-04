package com.gomel.taf.notebook_shop.pages;

import com.gomel.taf.framework.utils.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.gomel.taf.framework.ui.SeleniumUI.getInstance;

public class MainPage {
    //xpath patterns
    private static final String ITEM_TILE_PATTERN = "//*[@class='note-list row']/*[@class='col-3 mb-5'][%d]";
    private static final String CART_ITEM_PATTERN = "//li[contains(@class,'basket-item')][%d]";

    //constant xpath for cart
    private static final String CART_POPUP = "//*[@aria-labelledby='dropdownBasket']";
    private static final String CART_ITEM = "//ul[contains(@class,'list-group')]";
    private static final String CART_ITEM_NAME = "//*[@class='basket-item-title']";
    private static final String CART_ITEM_PRICE = "//*[@class='basket-item-price']";

    //constant xpath for item
    private static final String ITEM_TILE = "//*[@class='note-list row']/*[@class='col-3 mb-5']";
    private static final String ITEM_NAME = "//div[contains(@class,'product_name')]";
    private static final String ITEM_PRICE = "//span[contains(@class,'product_price')]";
    private static final String BUY_ITEM_BUTTON = "//button[contains(@class,'actionBuyProduct')]";
    private static final String NO_DISCOUNT = "/div[not(contains(@class,'hasDiscount'))]/*/following-sibling::*";

    private static final String CURRENT_PAGE = "//*[@class='page-item active']";

    //xpath for cart
    private final By cartDropdown = By.id("dropdownBasket");
    private final By cartCounter = By.xpath("//*[@id='basketContainer']/span");
    private final By cartPopup = By.xpath(CART_POPUP);
    private final By cartItemName = By.xpath(CART_ITEM + CART_ITEM_NAME);
    private final By cartItemPrice = By.xpath(CART_ITEM + CART_ITEM_PRICE);
    private final By cartTotalPrice = By.xpath(CART_POPUP + "//*[@class='basket_price']");
    private final By goToCartButton = By.xpath(CART_POPUP + "//a[@href='/basket']");
    private final By clearCartButton = By.xpath(CART_POPUP + "//div[contains(@class,'ClearBasket')]/a");

    private final By discountFilterCheckbox = By.id("gridCheck");

    private final By itemQuantityInput = By.xpath(ITEM_TILE + "//input[@name='product-enter-count']");

    private final By nextPageButton = By.xpath(CURRENT_PAGE + "/following-sibling::*");

    @Step(value = "Open Main page")
    public MainPage openPage() {
        getInstance().get(Constants.URL_MAIN);
        return this;
    }

    @Step(value = "Click cart dropdown")
    public MainPage clickCartDropdown() {
        if (!getInstance().isVisible(cartPopup)) {
            getInstance().click(cartDropdown);
        }
        return this;
    }

    @Step(value = "Click Clear cart button")
    public MainPage clickClearCartButton() {
        getInstance().click(clearCartButton);
        return this;
    }

    @Step(value = "Click Go to cart button")
    public MainPage clickGoToCartButton() {
        getInstance().click(goToCartButton);
        return this;
    }

    @Step(value = "Click Show only discount items checkbox")
    public MainPage checkShowOnlyDiscountItemsCheckbox() {
        if (!getInstance().waitForVisibilityOfElement(discountFilterCheckbox).isSelected()) {
            getInstance().click(discountFilterCheckbox);
        }
        return this;
    }

    @Step(value = "Input item quantity: {number}")
    public MainPage enterItemQuantity(int number) {
        getInstance().clearAndType(itemQuantityInput, Integer.toString(number));
        return this;
    }

    @Step(value = "Click Add item to cart button by index: {index}")
    public MainPage clickItemAddToCartButtonByIndex(int index) {
        getInstance().click(By.xpath(String.format(ITEM_TILE_PATTERN, index) + BUY_ITEM_BUTTON));
        return this;
    }

    @Step(value = "Click Add non-discount item to cart button by index: {index}")
    public MainPage clickNonDiscountItemAddToCartButtonByIndex(int index) {
        getInstance().click(By.xpath(String.format(ITEM_TILE_PATTERN, index) + NO_DISCOUNT + BUY_ITEM_BUTTON));
        return this;
    }

    @Step(value = "Wait until non-discount item not visible")
    public MainPage waitUntilNoDiscountItemsNotVisible() {
        getInstance().waitForNonVisibilityOfElement(By.xpath(String.format(ITEM_TILE_PATTERN, 1) + NO_DISCOUNT));
        return this;
    }

    @Step(value = "Click Next page button")
    public MainPage goToNextPage() {
        getInstance().click(nextPageButton);
        return this;
    }

    @Step(value = "Get non-discount item name by index: {index}")
    public String getNonDiscountItemNameByIndex(int index) {
        return getInstance().getText(By.xpath(String.format(ITEM_TILE_PATTERN, index) + NO_DISCOUNT + ITEM_NAME));
    }

    @Step(value = "Get item name by index: {index}")
    public String getItemNameByIndex(int index) {
        return getInstance().getText(By.xpath(String.format(ITEM_TILE_PATTERN, index) + ITEM_NAME));
    }

    @Step(value = "Get cart item name by index: {index}")
    public String getCartItemNameByIndex(int index) {
        return getInstance().getText(By.xpath(String.format(CART_ITEM_PATTERN, index) + CART_ITEM_NAME));
    }

    @Step(value = "Get non-discount item price by index: {index}")
    public int getNonDiscountItemPriceByIndex(int index) {
        String priceStr = getInstance()
                .getText(By.xpath(String.format(ITEM_TILE_PATTERN, index) + NO_DISCOUNT + ITEM_PRICE))
                .split(" ")[0];
        return Integer.parseInt(priceStr);
    }

    @Step(value = "Get item price by index: {index}")
    public int getItemPriceByIndex(int index) {
        String priceStr = getInstance().getText(By.xpath(String.format(ITEM_TILE_PATTERN, index) + ITEM_PRICE))
                .split(" ")[0];
        return Integer.parseInt(priceStr);
    }

    @Step(value = "Get cart item price by index: {index}")
    public int getCartItemPriceByIndex(int index) {
        String priceStr = getInstance().getText(By.xpath(String.format(CART_ITEM_PATTERN, index) + CART_ITEM_PRICE))
                .split(" ")[1];
        return Integer.parseInt(priceStr);
    }

    @Step(value = "Get cart quantity")
    public int getCartQuantity() {
        getInstance().isVisible(cartPopup);
        return Integer.parseInt(getInstance().getText(cartCounter));
    }

    @Step(value = "Get cart total price")
    public int getCartTotalPrice() {
        String priceStr = getInstance().getText(cartTotalPrice);
        return Integer.parseInt(priceStr);
    }

    @Step(value = "Get cart items name list")
    public List<String> getCartItemsNameList() {
        List<String> names = new ArrayList<>();
        List<WebElement> cartItems = getInstance().waitForVisibilityOfElements(cartItemName);
        for (WebElement item : cartItems) {
            names.add(item.getText());
        }
        return names;
    }

    @Step(value = "Get cart items price list")
    public List<Integer> getCartItemsPriceList() {
        List<Integer> prices = new ArrayList<>();
        List<WebElement> cartItems = getInstance().waitForVisibilityOfElements(cartItemPrice);
        for (WebElement item : cartItems) {
            String price = item.getText().split(" ")[1];
            prices.add(Integer.parseInt(price));
        }
        return prices;
    }

    @Step(value = "Check cart popup is visible")
    public boolean isCartPopupVisible() {
        return getInstance().isVisible(cartPopup);
    }

    @Step(value = "Check Cart page is loaded")
    public boolean isCartPageLoaded() {
        return getInstance().getCurrentUrl().contains("/basket");
    }
}
