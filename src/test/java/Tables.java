import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Tables {
    public static WebDriver driver;
    public static JavascriptExecutor jsx;
    public static List<WebElement> tableData;
    public static String FirstExpectedData = "Cierra";


    @BeforeClass
    public static void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        jsx = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @BeforeMethod
    public void openPage() {
        driver.navigate().to("https://demoqa.com/webtables");
    }

    @Test
    public void testTableData() throws InterruptedException {
        tableData = driver.findElements(By.xpath("//tbody/tr/td[1]"));
        String actualFirstData = tableData.get(0).getText();
        System.out.println("Actual First Data: " + actualFirstData);
        Assert.assertEquals(actualFirstData, FirstExpectedData);

    }

    @Test
    public void DeleteButtonTest() throws InterruptedException {
        String firstColumnData = driver.findElement(By.xpath("//td[normalize-space()='Cierra']")).getText();

        System.out.println("First Column Data: " + firstColumnData);

        driver.findElement(By.cssSelector("span[id='delete-record-1']")).click();

        List<WebElement> deletedElement = driver.findElements(By.xpath("//td[normalize-space()='Cierra']"));

        Assert.assertTrue(deletedElement.isEmpty());

        System.out.println("First Column Data is deleted");

    }


    @AfterClass
    public static void teardown() {
        driver.quit();
    }

}
