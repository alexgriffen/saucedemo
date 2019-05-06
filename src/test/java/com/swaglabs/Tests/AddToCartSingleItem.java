package com.swaglabs.Tests;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.Pages.CartPage;
import com.swaglabs.Pages.InventoryPage;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class AddToCartSingleItem extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void AddToCartSingleItemTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  	    driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage loginPage = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");
        InventoryPage inventoryPage = loginPage.enterCredentials("performance_glitch_user", "secret_sauce");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventoryPage.viewInventory().contains("Products"));

        this.annotate("Add To Cart Backpack...");
        inventoryPage.clickAddToCartBackpack();

        this.annotate("Go To Cart...");
        CartPage cart = inventoryPage.goToCart();

        this.annotate("Verify Backpack Item In Cart...");
        AssertJUnit.assertTrue(cart.verifyBackpackinCart().contains("Sauce Labs Backpack"));

    }

}
