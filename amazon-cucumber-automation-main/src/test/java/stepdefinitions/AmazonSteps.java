package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AmazonPage;
import utils.DriverFactory;

public class AmazonSteps {

    WebDriver driver;
    AmazonPage amazon;

    @Given("user launches Amazon website")
    public void user_launches_amazon_website() {
        driver = DriverFactory.initDriver();
        amazon = new AmazonPage(driver);
        amazon.openAmazon();
    }

    @Then("page title should contain {string}")
    public void page_title_should_contain(String title) {
        Assert.assertTrue(amazon.getTitle().contains(title));
    }

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        amazon.searchProduct(product);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(amazon.areResultsDisplayed());
    }

    @When("user applies brand filter")
    public void user_applies_brand_filter() {
        amazon.applyBrandFilter();
    }

    @Then("filtered results should be displayed")
    public void filtered_results_should_be_displayed() {
        Assert.assertTrue(amazon.areResultsDisplayed());
    }

    @When("user opens first product")
    public void user_opens_first_product() {
        amazon.openFirstProduct();
    }

    @Then("product title and price should be displayed")
    public void product_title_and_price_should_be_displayed() {
        Assert.assertTrue(amazon.isProductPageLoaded());
    }

    @When("user adds product to cart")
    public void user_adds_product_to_cart() {
        amazon.addProductToCart();
    }

    @Then("cart should be updated")
    public void cart_should_be_updated() {
        Assert.assertTrue(amazon.isAddedToCart());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
