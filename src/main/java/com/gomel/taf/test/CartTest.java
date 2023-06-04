package com.gomel.taf.test;

import com.gomel.taf.framework.ui.SeleniumUI;
import com.gomel.taf.notebook_shop.service.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

@Epic(value = "Notebook Shop")
@Feature(value = "Shopping cart")
public class CartTest {
    private LoginService loginService = new LoginService();
    private MainService mainService = new MainService();

    @BeforeSuite
    public void logInUser() {
        loginService.logIn();
    }

    @BeforeMethod()
    public void prepareTest() {
        mainService.clearCart();
    }

    @Story(value = "Check cart emptiness")
    @Test
    public void checkCartIsEmpty() {
        assertTrue(mainService.isCartPopupVisible(), "Cart dropdown is not visible");
        mainService.goToCart();
        assertTrue(mainService.isCartPageLoaded(), "Cart page is not loaded");
    }

    @Story(value = "Check adding one non-discount item to cart")
    @Test
    public void checkCartContainsOneNonDiscountedItem() {
        SoftAssert softAssert = new SoftAssert();
        String itemName = mainService.getNonDiscountItemNameByIndex(2);
        int itemPrice = mainService.getNonDiscountItemPriceByIndex(2);
        mainService.addNonDiscountItemToCartByIndex(2);

        softAssert.assertEquals(mainService.getCartQuantity(), 1, "Cart quantity is wrong");
        assertTrue(mainService.isCartPopupVisible(), "Cart dropdown is not visible");

        String cartItemName = mainService.getCartItemNameByIndex(1);
        int cartItemPrice = mainService.getCartItemPriceByIndex(1);
        int cartTotalPrice = mainService.getCartTotalPrice();

        softAssert.assertEquals(cartItemName, itemName, "Invalid item name in cart");
        softAssert.assertEquals(cartItemPrice, itemPrice, "Invalid item price in cart");
        softAssert.assertEquals(cartTotalPrice, itemPrice, "Invalid cart total price");

        mainService.goToCart();

        softAssert.assertTrue(mainService.isCartPageLoaded(), "Cart page is not loaded");
        softAssert.assertAll();
    }

    @Story(value = "Check adding one discount item to cart")
    @Test
    public void checkCartContainsOneDiscountedItem() {
        SoftAssert softAssert = new SoftAssert();
        mainService.addDiscountItemToCartByIndex(1);
        String itemName = mainService.getItemNameByIndex(1);
        int itemPrice = mainService.getItemPriceByIndex(1);

        softAssert.assertEquals(mainService.getCartQuantity(), 1, "Cart quantity is wrong");
        assertTrue(mainService.isCartPopupVisible(), "Cart dropdown is not visible");

        String cartItemName = mainService.getCartItemNameByIndex(1);
        int cartItemPrice = mainService.getCartItemPriceByIndex(1);
        int cartTotalPrice = mainService.getCartTotalPrice();

        softAssert.assertEquals(cartItemName, itemName, "Invalid item name in cart");
        softAssert.assertEquals(cartItemPrice, itemPrice, "Invalid item price in cart");
        softAssert.assertEquals(cartTotalPrice, itemPrice, "Invalid cart total price");

        mainService.goToCart();

        softAssert.assertTrue(mainService.isCartPageLoaded(), "Cart page is not loaded");
        softAssert.assertAll();
    }

    @Story(value = "Check adding nine different items to cart")
    @Test
    public void checkCartContainsNineItems() {
        SoftAssert softAssert = new SoftAssert();
        int quantityToAdd = 9;
        Map<String, Integer> itemsNameAndPrice = mainService.addManyItemsToCartByNumberGetNameAndPriceMap(quantityToAdd);

        softAssert.assertEquals(mainService.getCartQuantity(), quantityToAdd, "Cart quantity is wrong");
        assertTrue(mainService.isCartPopupVisible(), "Cart dropdown is not visible");

        List<String> cartItemNameList = mainService.getCartItemsNameList();
        List<Integer> cartItemPriceList = mainService.getCartItemsPriceList();
        int cartTotalPrice = mainService.getCartTotalPrice();

        softAssert.assertEquals(cartItemNameList, itemsNameAndPrice.keySet(), "Invalid items name in cart");
        softAssert.assertEquals(cartItemPriceList, itemsNameAndPrice.values(), "Invalid items price in cart");
        softAssert.assertEquals(cartTotalPrice, itemsNameAndPrice.values().stream().mapToInt(Integer::intValue).sum(), "Invalid cart total price");

        mainService.goToCart();

        softAssert.assertTrue(mainService.isCartPageLoaded(), "Cart page is not loaded");
        softAssert.assertAll();
    }

    @Story(value = "Check adding nine same discount items to cart")
    @Test
    public void checkCartContainsNineSameDiscountItem() {
        SoftAssert softAssert = new SoftAssert();
        int quantityToAdd = 9;
        mainService.addManySameDiscountItemToCartByIndex(1, quantityToAdd);

        String itemName = mainService.getItemNameByIndex(1);
        int itemPrice = mainService.getItemPriceByIndex(1);

        softAssert.assertEquals(mainService.getCartQuantity(), quantityToAdd, "Cart quantity is wrong");
        assertTrue(mainService.isCartPopupVisible(), "Cart dropdown is not visible");

        String cartItemName = mainService.getCartItemNameByIndex(1);
        int cartItemPrice = mainService.getCartItemPriceByIndex(1);
        int cartTotalPrice = mainService.getCartTotalPrice();

        softAssert.assertEquals(cartItemName, itemName, "Invalid item name in cart");
        softAssert.assertEquals(cartItemPrice, itemPrice * quantityToAdd, "Invalid item price in cart");
        softAssert.assertEquals(cartTotalPrice, itemPrice * quantityToAdd, "Invalid cart total price");

        mainService.goToCart();

        softAssert.assertTrue(mainService.isCartPageLoaded(), "Cart page is not loaded");
        softAssert.assertAll();
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        SeleniumUI.getInstance().stopBrowser();
    }
}
