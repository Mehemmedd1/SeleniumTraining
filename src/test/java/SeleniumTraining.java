
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTraining {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");

        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.navigate().to("https://demoqa.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().to("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("John Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("currentAddress")).sendKeys("123 Main St, Anytown, USA");
        driver.findElement(By.id("permanentAddress")).sendKeys("456 Elm St, Anytown, USA");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(2000);

        driver.quit();

    }
}
