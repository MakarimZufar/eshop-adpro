package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    private int serverPort = 8080;

    private String baseUrl;

    private String urlListProduct = "/product/create";

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("http://localhost:%d%s", serverPort,urlListProduct);
    }

    @Test
    void testCreatProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        nameInput.sendKeys("Test Product");
        quantityInput.sendKeys("10");
        submitButton.click();

        driver.get("http://localhost:" + serverPort + "/product/list");
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Test Product')]") );

        assertTrue(productName.isDisplayed());
    }
}
