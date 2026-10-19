import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DynamicProperties {
    static WebDriver driver;
    static JavascriptExecutor jsx;
    static SoftAssert softAssert;

    static WebElement elementsButton;
    static WebElement dynamicPropertiesButton;
    static WebElement enabledButton;
    static WebElement visibleButton;

    static String firstRandomIdAttribute;
    static String secondRandomIdAttribute;
    static String firstColorChangeAttribute;
    static String secondColorChangeAttribute;


    private WebDriverWait waitFor(int sec){
        return new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    @BeforeClass
    public static void setup() {
        driver = new FirefoxDriver();
        softAssert=new SoftAssert();
        jsx = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeAllTests(){
        driver.navigate().to("https://demoqa.com/");
        jsx.executeScript("window.scrollBy(0, 350)");
        WebDriverWait wait = waitFor(10);

        elementsButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[normalize-space()='Elements']")));
        elementsButton.click();
        jsx.executeScript("window.scrollBy(0, 350)");
        dynamicPropertiesButton=driver.findElement(By.xpath("//span[normalize-space()='Dynamic Properties']"));
        dynamicPropertiesButton.click();


    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void test01(){
        firstRandomIdAttribute=driver.findElement(By.xpath("//p[normalize-space()='This text has random Id']")).getAttribute("id");
        System.out.println("First Random Id Attribute: " + firstRandomIdAttribute);
        driver.navigate().refresh();
        secondRandomIdAttribute=driver.findElement(By.xpath("//p[normalize-space()='This text has random Id']")).getAttribute("id");
        System.out.println("Second Random Id Attribute: " + secondRandomIdAttribute);
        softAssert.assertNotEquals(firstRandomIdAttribute, secondRandomIdAttribute);
        softAssert.assertAll();

    }
    @Test
    public void test02(){
        enabledButton=driver.findElement(By.xpath("//button[@id='enableAfter']"));
        softAssert.assertFalse(enabledButton.isEnabled());

        WebDriverWait wait = waitFor(5);
        wait.until(ExpectedConditions.elementToBeClickable(enabledButton));
        softAssert.assertTrue(enabledButton.isEnabled());

        softAssert.assertAll();

    }
    @Test
    public void test03(){
        firstColorChangeAttribute=driver.findElement(By.xpath("//button[@id='colorChange']")).getAttribute("class");
        System.out.println("First Color Change Attribute: " + firstColorChangeAttribute);
        WebDriverWait wait = waitFor(5);
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//button[@id='colorChange']"), "class", "mt-4 text-danger btn btn-primary"));
        secondColorChangeAttribute=driver.findElement(By.xpath("//button[@id='colorChange']")).getAttribute("class");
        System.out.println("Second Color Change Attribute: " + secondColorChangeAttribute);
        softAssert.assertNotEquals(firstColorChangeAttribute, secondColorChangeAttribute);
        softAssert.assertAll();
    }
    @Test
    public void test04(){
        List<WebElement> visibleButtons=driver.findElements(By.xpath("//button[@id='visibleAfter']"));
        if (visibleButtons.isEmpty() || visibleButtons.get(0).isDisplayed()){
            System.out.println("UnVisible button is visible");
        }

        WebDriverWait wait = waitFor(5);
        visibleButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='visibleAfter']")));
        softAssert.assertTrue(visibleButton.isDisplayed(), "Visible button is not visible");
        softAssert.assertAll();

    }
}
