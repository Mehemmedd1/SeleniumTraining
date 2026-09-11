
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class SeleniumTraining {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        String fullname = "John Doe";
        String email="john.doe@example.com";
        String address="123 Main St, Anytown, USA";
        String address2="456 Elm St, Anytown, USA";

        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.navigate().to("https://demoqa.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().to("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys(fullname);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("currentAddress")).sendKeys(address);
        driver.findElement(By.id("permanentAddress")).sendKeys(address2);
        driver.findElement(By.id("submit")).click();
        JavascriptExecutor jsx= (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0, 350)");
        Thread.sleep(2000);

        String actualName=driver.findElement(By.id("name")).getText();
        String actualEmail=driver.findElement(By.id("email")).getText();
        String actualAddress=driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        String actualAddress2=driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();

        Assert.assertEquals(actualName.substring(5), fullname);
        Assert.assertEquals(actualEmail.substring(6), email);
        Assert.assertEquals(actualAddress.substring(17), address);
        Assert.assertEquals(actualAddress2.substring(20), address2);

        System.out.println("Actual Name: " + actualName);
        System.out.println("Actual Email: " + actualEmail);
        System.out.println("Actual Address: " + actualAddress);
        System.out.println("Actual Address 2: " + actualAddress2);

        driver.quit();

    }
}
