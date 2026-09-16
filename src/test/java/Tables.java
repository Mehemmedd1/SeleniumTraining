import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Tables {
    public static WebDriver driver;
    public static JavascriptExecutor jsx;
    public static  int millis=2000;
    public static List<WebElement> tableData;
    public static String FirstExpectedData="Cierra";


    @BeforeClass
    public static void setup(){
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/webtables");
        jsx=(JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Test
    public void testTableData() throws InterruptedException {
        tableData=driver.findElements(By.xpath("//tbody/tr/td[1]"));
        String actualFirstData=tableData.get(0).getText();
        System.out.println("Actual First Data: "+actualFirstData);
        Assert.assertEquals(actualFirstData, FirstExpectedData);

    }
    @Test
    public void DeleteButtonTest() throws InterruptedException {
        String firstColumnData=driver.findElement(By.xpath("//td[normalize-space()='Cierra']")).getText();
        System.out.println("First Column Data: "+firstColumnData);
        driver.findElement(By.cssSelector("span[id='delete-record-1'] svg path")).click();
        Thread.sleep(millis);
        if(firstColumnData.equals(FirstExpectedData)){
            System.out.println("First Column Data is not deleted");
        }else{
            System.out.println("First Column Data is deleted");
        }


    }




    @AfterClass
    public static void teardown(){
        driver.quit();
    }

}
