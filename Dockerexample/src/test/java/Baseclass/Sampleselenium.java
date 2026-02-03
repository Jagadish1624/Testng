package Baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Sampleselenium {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        // REQUIRED for headless
        options.addArguments("--headless");   
        options.addArguments("--window-size=1920,1080");

        // Recommended for stability (especially Linux / CI)
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.google.com");

        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }


}
