package com.gomel.taf.notebook_shop.service;

import com.gomel.taf.notebook_shop.pages.MainPage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainService {
    private MainPage mainPage = new MainPage();

    public void clearCart() {
        if (mainPage.isCartPageLoaded()) {
            mainPage.openPage();
        }
        if (mainPage.getCartQuantity() > 0) {
            mainPage.clickCartDropdown()
                    .clickClearCartButton();
        }
        mainPage.openPage();
    }

    public void goToCart() {
        mainPage.clickCartDropdown()
                .clickGoToCartButton();
    }

    public void addNonDiscountItemToCartByIndex(int index) {
        mainPage.clickNonDiscountItemAddToCartButtonByIndex(index);
    }

    public void addDiscountItemToCartByIndex(int index) {
        mainPage.checkShowOnlyDiscountItemsCheckbox()
                .waitUntilNoDiscountItemsNotVisible()
                .clickItemAddToCartButtonByIndex(index);
    }

    public void addManySameDiscountItemToCartByIndex(int index, int number) {
        mainPage.checkShowOnlyDiscountItemsCheckbox()
                .waitUntilNoDiscountItemsNotVisible()
                .enterItemQuantity(number)
                .clickItemAddToCartButtonByIndex(index);
    }

    public String getItemNameByIndex(int index) {
        return mainPage.getItemNameByIndex(index);
    }

    public int getItemPriceByIndex(int index) {
        return mainPage.getItemPriceByIndex(index);
    }

    public String getCartItemNameByIndex(int index) {
        return mainPage.clickCartDropdown()
                .getCartItemNameByIndex(index);
    }

    public int getCartItemPriceByIndex(int index) {
        return mainPage.clickCartDropdown()
                .getCartItemPriceByIndex(index);
    }

    public String getNonDiscountItemNameByIndex(int index) {
        return mainPage.getNonDiscountItemNameByIndex(index);
    }

    public int getNonDiscountItemPriceByIndex(int index) {
        return mainPage.getNonDiscountItemPriceByIndex(index);
    }

    public int getCartTotalPrice() {
        return mainPage.clickCartDropdown()
                .getCartTotalPrice();
    }

    public boolean isCartPopupVisible() {
        return mainPage.clickCartDropdown()
                .isCartPopupVisible();
    }

    public int getCartQuantity() {
        return mainPage.getCartQuantity();
    }

    public boolean isCartPageLoaded() {
        return mainPage.isCartPageLoaded();
    }

    public Map<String, Integer> addManyItemsToCartByNumberGetNameAndPriceMap(int number) {
        Map<String, Integer> itemsNameAndPrice = new LinkedHashMap<>();
        int pages = number / 8 + 1;
        int count = number;
        for (int i = 1; i < pages + 1; i++) {
            if (count > 8) {
                for (int j = 1; j < 9; j++) {
                    mainPage.clickItemAddToCartButtonByIndex(j);
                    itemsNameAndPrice.put(mainPage.getItemNameByIndex(j), mainPage.getItemPriceByIndex(j));
                }
                mainPage.goToNextPage().isCartPopupVisible();
            } else {
                for (int j = 1; j < count + 1; j++) {
                    mainPage.clickItemAddToCartButtonByIndex(j);
                    itemsNameAndPrice.put(mainPage.getItemNameByIndex(j), mainPage.getItemPriceByIndex(j));
                }
            }
            count -= 8;
        }
        return itemsNameAndPrice;
    }

    public List<String> getCartItemsNameList() {
        return mainPage.clickCartDropdown().getCartItemsNameList();
    }

    public List<Integer> getCartItemsPriceList() {
        return mainPage.getCartItemsPriceList();
    }
}
