package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonPage {

    WebDriver driver;
    WebDriverWait webDriverWait;

    @FindBy(css = "input#twotabsearchtextbox")
    WebElement searchTextBox;

    @FindBy(css = "div.s-main-slot div[data-index='1'] h2.a-size-mini a.a-text-normal")
    WebElement searchResultFirstElement;

    @FindBy(css = "div#buyNew_cbb span#newBuyBoxPrice")
    WebElement itemOriginalPrice;

    @FindBy(css = "div.s-main-slot div[data-index='1'] span.a-price-symbol")
    WebElement priceSymbol;

    @FindBy(css = "div.s-main-slot div[data-index='1'] span.a-price-whole")
    WebElement wholePrice;

    @FindBy(css = "div.s-main-slot div[data-index='1'] span.a-price-fraction")
    WebElement decimalPrice;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartBtn;

    @FindBy(css = "div.huc-v2-table-col span.a-color-price")
    WebElement checkoutPrice;

    @FindBy(id = "hlb-ptc-btn-native")
    WebElement proceedToCheckoutButton;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void searchSomeThingInAmazon(String searchItem) {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchTextBox));
        searchTextBox.clear();
        searchTextBox.sendKeys(searchItem);
        searchTextBox.submit();
    }

    public String getSearchResultFirstItemPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchResultFirstElement));
        StringBuilder originalPrice = new StringBuilder();
        originalPrice.append(priceSymbol.getText());
        originalPrice.append(wholePrice.getText());
        originalPrice.append(".");
        originalPrice.append(decimalPrice.getText());
        return originalPrice.toString();
    }

    public void clickOnFirstSearchResultItem() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchResultFirstElement));
        searchResultFirstElement.click();
    }

    public String getPriceOnAddToCartPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(itemOriginalPrice));
        return itemOriginalPrice.getText();
    }

    public void clickOnAddToCartButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }

    public String getPriceOnProceedToCheckoutPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkoutPrice));
        return checkoutPrice.getText();
    }

    public void clickOnProceedToCheckoutBtn() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }
}
