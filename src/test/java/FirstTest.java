import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class FirstTest {

     AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException
    {
        // String appiumServerUrl = "http://127.0.0.1:4723";

         DesiredCapabilities dc = new DesiredCapabilities();
         dc.setCapability("platformName","Android");
         dc.setCapability("appium:automationName","UiAutomator2");
         dc.setCapability("appium:deviceName", "Android");
         dc.setCapability("appium:appWaitActivity", "*");
         dc.setCapability("appium:appWaitDuration", 60000);
         dc.setCapability("appium:app",System.getProperty("user.dir")+"\\apps\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        URL url = URI.create("http://127.0.0.1:4723").toURL();
         driver = new AndroidDriver(url, dc);
     }

     @Test
    public void test_01()
     {
         System.out.println("Test_01 : Perform Login functionality using given credentials");
         driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='standard_user']")).click();
         System.out.println("Pass :: Valid credentials have been entered using AutofilSetup");
         driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='LOGIN']")).click();
         System.out.println("Pass : User has successfully logged in using the provided credentials\n");
     }

   @Test
   public void test_02()
    {
        System.out.println("Test_02 : Attempt to add any two products to your cart.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)"))).click();
       // driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)")).click();
        System.out.println("Pass :: One item has been added to the shopping cart");
        System.out.println(">>>>> Product Listing Page -- Updates <<<<<");

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)"))).click();
      //  driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"test-ADD TO CART\").instance(1)")).click();
        System.out.println(" Pass :: Two items have been added to your shopping cart\n");
    }

    @Test
    public void test_03() {
        System.out.println("Test_02 :Continue with the checkout procedure and provide the necessary information.");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)")).click();
        System.out.println(">>>>> Cart page has been opened <<<<<");
        System.out.println(">>>>> Navigate to the checkout section <<<<<");
        //driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().text(\"CHECKOUT\"))")).click();
        System.out.println(">>>>> Providing delivery  details<<<<<");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        WebElement First_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-First Name")));
        First_Name.click();
        First_Name.clear();
        First_Name.sendKeys("Virat ");
        //driver.findElement(AppiumBy.accessibilityId("test-First Name")).click();
        WebElement Last_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Last Name")));
        Last_Name.click();
        Last_Name.clear();
        Last_Name.sendKeys("Kohli ");
        WebElement Zip = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("test-Zip/Postal Code")));
        Zip.click();
        Zip.clear();
        Actions actions = new Actions(driver);
        actions.sendKeys("682025").perform();
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        System.out.println(">>>>> Proceedings continue in shipping<<<<<");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE\")")).click();

        System.out.println(">>>>> Completing shipment<<<<<\n");
        //driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().description(\"test-FINISH\"))")).click();
    }

    @Test
    public void test_04()
    {
        System.out.println(">>>>> Transaction has been completed, and  returning home<<<<<");
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
        System.out.println(">>>>> Opening the hamburger menu<<<<<");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")).click();
        System.out.println(">>>>> Navigate to the logout option & Proceed to log out<<<<<");
        driver.findElement(AppiumBy.accessibilityId("test-LOGOUT")).click();
        System.out.println(">>>>> Successfully logged out and returned to the login section.<<<<<");





    }

@AfterTest
    public void close()
     {
        // driver.quit();
     }
}


