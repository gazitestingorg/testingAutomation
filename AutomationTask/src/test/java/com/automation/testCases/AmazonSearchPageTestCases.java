package com.automation.testCases;

import com.google.common.io.Resources;
import com.automation.pages.AmazonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AmazonSearchPageTestCases {

    private WebDriver driver;
    private AmazonPage amazonPage;
    private String listingPrice, OS;


    @BeforeTest
    public void setup() {
        OS = System.getProperty("os.name").toLowerCase();
        if (isMac()) {
            System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        } else if (isWindows()) {
            System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver.exe").getPath());
        }
        driver = new ChromeDriver();
        driver.get("https://amazon.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        amazonPage = new AmazonPage(driver);
    }

    @Test(priority = 1)
    public void verifyListingPriceAndAddToCartPrice() {
        amazonPage.searchSomeThingInAmazon("qa testing for beginners");
        listingPrice = amazonPage.getSearchResultFirstItemPrice();
        amazonPage.clickOnFirstSearchResultItem();
        String addToCartPrice = amazonPage.getPriceOnAddToCartPage();
        Assert.assertEquals(addToCartPrice, listingPrice, "Listing Price and AddToCart Price both should be equal");
    }

    @Test(priority = 2)
    public void verifyListingPriceAndProceedCheckoutAssetPrice() {
        amazonPage.clickOnAddToCartButton();
        String checkOutAssetPagePrice = amazonPage.getPriceOnProceedToCheckoutPage();
        Assert.assertEquals(checkOutAssetPagePrice, listingPrice, "Listing Price and Checkout Asset Page Price both should be equal");
    }

    @Test(priority = 3, enabled = false)
    public void verifyListingPriceAndCheckOutPrice() {
        amazonPage.clickOnProceedToCheckoutBtn();
        //Need Username and wped to login in to Amazon and also it is asking for mobile verification to login
        Assert.assertEquals("", listingPrice, "Listing Price and final Checkout Page Price both should be equal");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

}
