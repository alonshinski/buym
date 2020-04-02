import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testsPage {
    static String driverPath = "C:\\Users\\Alon\\Desktop\\selenium\\drivers\\chromedriver.exe";
    static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest myTests;
    static String ImagesPath = "C:\\Users\\Alon\\Desktop\\selenium\\screenShots";
    private registrtionScreen rgScreen;
    private homeScreen hmscreen;
    private chooseGiftScreen giftScreen;
    private senderAndReceiverInformationScreen senderAndRecieverInfoScreen;
    private generalPage gp;
    private loadingScreen lg;


    @Rule
    public TestName name = new TestName();

    {
    }


    @BeforeClass
    public static void setUp() throws Exception {
        extent = new ExtentReports("C:\\Users\\Alon\\Desktop\\selenium\\myReports\\my_02report.html");
        extent.loadConfig(new File("C:\\Users\\Alon\\Desktop\\selenium\\Myconfigs\\reportConfig.xml"));
        //System.setProperty("webdriver.chrome.driver", driverPath);
        String browser = generalPage.readFromFile("browser");
        setBrowser(browser);
    }

    @Before
    public void myBeforeTests() {
        rgScreen = new registrtionScreen(driver);
        hmscreen = new homeScreen(driver);
        giftScreen = new chooseGiftScreen(driver);
        senderAndRecieverInfoScreen = new senderAndReceiverInformationScreen(driver);
        lg = new loadingScreen(driver);
    }



    @Test
    public void test_01_registration() throws Exception {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        try {
            rgScreen.registrationProcess();
            myTests.log(LogStatus.INFO, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }
        catch (Exception TimeoutException) {
            myTests.log(LogStatus.FAIL, "TimeoutException");
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));

        }
        String expected = generalPage.readFromFile("registrationDone");
        String actual = driver.getCurrentUrl();
        try {
            System.out.println(actual);
            Assert.assertEquals(expected, actual);
            myTests.log(LogStatus.PASS, "The url are equal");
            myTests.log(LogStatus.PASS, "Registration done!");
        } catch (AssertionError e) {
            myTests.log(LogStatus.INFO, "The urls are not equal " + e);
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));

        }
        myTests.log(LogStatus.INFO, "End of the test" + name.getMethodName());
    }

    @Test
    public void test_02_homeScreen() throws InterruptedException {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        try {
            WebElement ele = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(hmscreen.myAccount));
            myTests.log(LogStatus.PASS, "field my account found");
        } catch (Exception TimeoutException) {
            myTests.log(LogStatus.FAIL, "field my account didn't found");
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }

        try {
            hmscreen.homeScreenChooseGiftOptions();
        } catch (Exception NoSuchElementException) {
            myTests.log(LogStatus.FAIL, "ElementClickInterceptedException");
        }

        try {
            Assert.assertTrue(hmscreen.myAccount.isDisplayed());
            myTests.log(LogStatus.PASS, "press on search button to find a gift");
        } catch (AssertionError | Exception NoSuchElementException) {
            myTests.log(LogStatus.FAIL, "couldn't press on search button to find a gift");
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }
        myTests.log(LogStatus.INFO, "End of the test " + name.getMethodName());
    }

    @Test
    public void test_03_chooseGiftScreen() throws Exception {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String expectedUrl = generalPage.readFromFile("urlChanged");
        String urlAfterReplaceAll = expectedUrl.replaceAll("amp;", "");
        System.out.println(urlAfterReplaceAll);
        try {
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(urlAfterReplaceAll, actualUrl);
            myTests.log(LogStatus.PASS, "urls equal");
        } catch (AssertionError e) {
            myTests.log(LogStatus.FAIL, "The urls are not equal" + e);
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(giftScreen.pickABuisness));
            myTests.log(LogStatus.PASS, "element of business photo visible");
        } catch (Exception TimeoutException) {
            myTests.log(LogStatus.FAIL, "can't click on element of business photo");
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }

        try {
            giftScreen.scrollInToElement();
            myTests.log(LogStatus.PASS, "Successfully scorlled in to Element");
        } catch (Exception error) {
            myTests.log(LogStatus.FAIL, "NoSuchElementException");
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(giftScreen.chooseABuisness));
            WebElement element = giftScreen.chooseABuisness;
            element.click();
            myTests.log(LogStatus.PASS, "picked a business successfully");
        } catch (Exception error) {
            myTests.log(LogStatus.FAIL, " picked a business Faild");
        }
        Thread.sleep(2000);
        myTests.log(LogStatus.INFO, "End of the test stage 1 " + name.getMethodName());
    }

    @Test
    public void test_04_senderAndReciver() throws Exception {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        try {
            Assert.assertTrue(driver.getCurrentUrl().contains("package"));
            myTests.log(LogStatus.PASS, "Stage 2 is displayed the assert true passed");
            myTests.log(LogStatus.PASS, "test passed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }
        catch (AssertionError error) {
            myTests.log(LogStatus.FAIL, "Stage 1 failed " + error);
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }
            String colorExpected=generalPage.readFromFile("yellowText");

           WebDriverWait wait = new WebDriverWait(driver, 5);

       try {
           wait.until(ExpectedConditions.visibilityOf(senderAndRecieverInfoScreen.colorElement));
           myTests.log(LogStatus.PASS, "Element of highlighted 2 found");
       }
       catch (Exception error){
           myTests.log(LogStatus.FAIL, "Element of highlighted 2 not found");
       }
         String actualColor="";
         try {
          actualColor = senderAndRecieverInfoScreen.colorElement.getCssValue("color");
          myTests.log(LogStatus.PASS, "Saved the color of element");
         System.out.println(actualColor);
         }
     catch (Exception NoSuchElementException) {
          myTests.log(LogStatus.FAIL, "can't get the color of element");
         }

        try {
            Assert.assertEquals(colorExpected,actualColor);
            myTests.log(LogStatus.PASS, "The color code is good and assertEquals passed");
            senderAndRecieverInfoScreen.mytest04Actions();
        } catch (Exception|AssertionError error) {
            myTests.log(LogStatus.FAIL, "NoSuchElementException");
        }
            String sender="";
            String receiver="";
            String blessing="";
        try {
            sender=senderAndRecieverInfoScreen.sender.getText();
            receiver=senderAndRecieverInfoScreen.receiver.getText();
            blessing=senderAndRecieverInfoScreen.blessing.getText();
            myTests.log(LogStatus.PASS, "element found with text " +sender);
            myTests.log(LogStatus.PASS, "element found with text " +receiver);
            myTests.log(LogStatus.PASS, "element found with text " +blessing);
        }
        catch (Exception NoSuchElementException){
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
        }
        try {
            String expectedSender=generalPage.readFromFile("fromWhoGift");
            Assert.assertEquals(expectedSender,sender);
            myTests.log(LogStatus.PASS, "assertion sender passed");
        }
        catch (AssertionError error){
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
        }
        try {
            String expectedReceiver=generalPage.readFromFile("receiver");
            Assert.assertEquals(expectedReceiver,receiver);
            myTests.log(LogStatus.PASS, "assertion receiver passed");
        }
        catch (AssertionError error){
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
        }
        try {
            String expectedBlessing=generalPage.readFromFile("blessing");
            Assert.assertEquals(expectedBlessing,blessing);
            myTests.log(LogStatus.PASS, "assertion blessing passed");
        }
        catch (AssertionError error){
            myTests.log(LogStatus.FAIL, "NoSuchElementException ");
        }


        try {
            Assert.assertTrue(driver.getCurrentUrl().contains("payment"));
            myTests.log(LogStatus.PASS, "The assertTrue passed");
            myTests.log(LogStatus.PASS, "Test passed",
                    myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        } catch (AssertionError error) {
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
            myTests.log(LogStatus.FAIL, "The assertTrue failed");
        }
        myTests.log(LogStatus.INFO, "End of the test stage 2 " + name.getMethodName());
    }

    @Test public void
    test_05_extrasAssertRedText() throws Exception {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to("https://buyme.co.il");
    try {
        wait.until(ExpectedConditions.visibilityOf(hmscreen.myAccount));
        myTests.log(LogStatus.PASS, "element is visible ");
    }
    catch (TimeoutException error){
        myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis()));
        myTests.log(LogStatus.FAIL, "TimeoutException failed: waiting for visibility ");
    }
      try {
          hmscreen.loginRedText();
          myTests.log(LogStatus.PASS, "element of myAccount found ");
      }
      catch (Exception error){
          myTests.log(LogStatus.FAIL, "element of myAccount didn't found ");
      }
            String actualText="";
            String expectedText=generalPage.readFromFile("redText");
            try {
                actualText=hmscreen.getTheRedText.getText();
                myTests.log(LogStatus.PASS, "element of red text found ");
            }
            catch (Exception NoSuchElementException ){
                myTests.log(LogStatus.FAIL, "element of red text didn't found ");
            }
            System.out.println(actualText);
        try{
            Assert.assertEquals(expectedText,actualText);
            myTests.log(LogStatus.PASS, "the assertion passed ");
        }
        catch (AssertionError error){
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
            myTests.log(LogStatus.FAIL, "the assertion failed");
        }
       try {
           hmscreen.exit.click();
           myTests.log(LogStatus.PASS, "clicked on exit");
       }
       catch (Exception NoSuchElementException){
           myTests.log(LogStatus.FAIL, "click on exit failed");
       }
        myTests.log(LogStatus.INFO, "End of the test " + name.getMethodName());
    }//catch assert red text Extra test number 2...

    @Test public void
    test_06_scrollToButtonExtras(){
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to("https://buyme.co.il/search");
        try{
            Assert.assertTrue(giftScreen.elementOfSearch.isDisplayed());
            myTests.log(LogStatus.PASS, "The assertTrue passed");
        }
        catch (AssertionError error){
            myTests.log(LogStatus.PASS, "The assertTrue failed you didn't get to right page");
        }
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            wait.until(ExpectedConditions.visibilityOf(giftScreen.scrollToButton));
            myTests.log(LogStatus.PASS, "Scorlled to the bottom");
            Thread.sleep(1000);
            myTests.log(LogStatus.PASS, "Test passed",
                    myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
        }
        catch (Exception error){
            myTests.log(LogStatus.FAIL, "Test failed",
                    myTests.addScreenCapture(testsPage.takeScreenShot(ImagesPath + "\\" + System.currentTimeMillis())));
            myTests.log(LogStatus.FAIL, "couldn't scroll to the bottom");
            myTests.log(LogStatus.INFO, "End of the test  " + name.getMethodName());
        }
        myTests.log(LogStatus.INFO, "End of the test " + name.getMethodName());
    }// scroll To Button Extras test 3...


    /*@Test
    public void test_07_extrasGetSize() throws InterruptedException, AWTException {
        myTests = extent.startTest(name.getMethodName());
        myTests.log(LogStatus.INFO, "Start of the Test");
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.get("https://buyme.co.il");
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bounce1")));
        Robot robot =new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(3000);
        Dimension ele=driver.findElement(By.cssSelector(".bounce1")).getSize();
        System.out.println(ele);
        myTests.log(LogStatus.INFO, "End of the test" + name.getMethodName());
    }*/
 //catch dot element ExtraTest 1...
    @After
    public void myAfter(){
        extent.endTest(myTests);
    }


    @AfterClass
    public static void myAfterClass(){
        driver.close();
        driver.quit();
        extent.flush();
    }
    private static void setBrowser(String browser) {
        switch (browser) {
            case "Chrome":
               System.setProperty("webdriver.chrome.driver", "C://Users//Alon//Desktop//selenium//drivers//chromedriver.exe");
               ChromeOptions option = new ChromeOptions();
                option.addArguments("-incognito");
                option.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(option); //switch for choosing browser case chrome with incognito...


               /* System.setProperty("webdriver.chrome.driver", "C://Users//Alon//Desktop//selenium//drivers//chromedriver.exe");
                ChromeOptions opt = new ChromeOptions();
                opt.setPageLoadStrategy(PageLoadStrategy.NONE);
                driver = new ChromeDriver(opt); //it's for dot catch element...*/
                break;


            case "FireFox":
                System.setProperty("webdriver.gecko.driver", "C://Users//Alon//Desktop//selenium//drivers//geckodriver.exe");
                File pathBinary = new File("C:\\Users\\carmel\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
                FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
                DesiredCapabilities desired = DesiredCapabilities.firefox();
                FirefoxOptions options = new FirefoxOptions();
                desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
                driver = new FirefoxDriver(options);

                break;
        }

    }
    static String takeScreenShot(String ImagesPath) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ImagesPath + ".png");
            try {
                FileUtils.copyFile(screenShotFile, destinationFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        return ImagesPath + ".png";
    }

}
