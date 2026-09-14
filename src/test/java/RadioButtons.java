import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class RadioButtons {
    public static void main(String[] args) throws InterruptedException {

        String expectedYesText="Yes";
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/radio-button");
        WebElement clickableYEsRadioButton=driver.findElement(By.xpath("//input[@id='yesRadio']"));
        clickableYEsRadioButton.click();
        Thread.sleep(2000);
        String actualSuccessText=driver.findElement(By.className("text-success")).getText();
        Assert.assertEquals(actualSuccessText, expectedYesText, "Radio button is not working");


        WebElement NotClickableNoRadioButton1=driver.findElement(By.id("noRadio"));

        if (!NotClickableNoRadioButton1.isEnabled()) {
            System.out.println("No radio disabled-dir, klik oluna bilmir");
        } else {
            System.out.println("No radio enabled-dir");
        }




    }
}
