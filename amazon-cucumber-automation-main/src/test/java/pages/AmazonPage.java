package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AmazonPage {

    WebDriver driver;
    WebDriverWait wait;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // LOCATORS
    By searchBox = By.id("twotabsearchtextbox");
    By searchBtn = By.id("nav-search-submit-button");
    By searchResults = By.cssSelector("div[data-component-type='s-search-result']");
    By productLinks = By.cssSelector("div[data-component-type='s-search-result'] h2");
    By addToCartBtn = By.id("add-to-cart-button");
    By brandFilterCheckbox = By.xpath("(//span[text()='Samsung' or text()='Redmi'])[1]");

    // ACTIONS
    public void openAmazon() {
        driver.get("https://www.amazon.in");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchProduct(String product) {
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.clear();
        box.sendKeys(product);
        driver.findElement(searchBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
    }

    public boolean areResultsDisplayed() {
        return driver.findElements(searchResults).size() > 0;
    }

    public void applyBrandFilter() {
        try {
            WebElement brand = wait.until(ExpectedConditions.elementToBeClickable(brandFilterCheckbox));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brand);
            brand.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
        } catch (Exception e) {
            System.out.println("Brand filter not available, skipping.");
        }
    }

    public void openFirstProduct() {
        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks)
        );

        WebElement first = products.get(0);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", first);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);

        String parent = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }

    public boolean isProductPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
        return driver.findElement(addToCartBtn).isDisplayed();
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public boolean isAddedToCart() {
        return driver.getPageSource().contains("Added to Cart");
    }
}
