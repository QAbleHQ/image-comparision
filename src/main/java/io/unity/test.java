package io.unity;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {


    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        image_comparison abc = new image_comparison(driver);


        driver.get("https://www.google.com");
    abc.compare_image("google");
    }
}
