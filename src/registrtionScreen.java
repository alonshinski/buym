import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class registrtionScreen {
     WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest myTests;
    static String ImagesPath="C:\\Users\\Alon\\Desktop\\selenium\\screenShots";
    private  registrtionScreen rgScreen;
    private homeScreen hmscreen;
    private chooseGiftScreen giftScreen;
    private senderAndReceiverInformationScreen senderAndRecieverInfoScreen;
    private generalPage gp;

    @Before
    public void myBeforeTests() {
        rgScreen = new  registrtionScreen(driver);
        hmscreen=new homeScreen(driver);
        giftScreen=new  chooseGiftScreen(driver);
        senderAndRecieverInfoScreen=new senderAndReceiverInformationScreen(driver);
    }

    @FindBy(css="input[placeholder=\"שם פרטי\"]") WebElement userNameField;
    @FindBy(css="input[placeholder=\"מייל\"]")WebElement email;
    @FindBy(css="input[placeholder=\"סיסמה\"]") WebElement pass;
    @FindBy(css="input[placeholder=\"אימות סיסמה\"]") WebElement passValidation;
    @FindBy(css="button[class=\"ui-btn orange large\"]")WebElement clickOnRegistertionButton;



    public registrtionScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void registrationProcess() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://buyme.co.il/");
        driver.manage().window().maximize();
        rgScreen = new  registrtionScreen(driver);
        hmscreen=new homeScreen(driver);
        hmscreen.loginAndRegister.click();
        hmscreen.Register.click();
        Thread.sleep(1000);
        rgScreen.userNameField.click();
        Thread.sleep(1000);
        rgScreen.userNameField.sendKeys(generalPage.readFromFile("firstName"));
        Thread.sleep(1000);
        rgScreen.email.sendKeys(generalPage.readFromFile("email"));
        Thread.sleep(1000);
        rgScreen.pass.sendKeys(generalPage.readFromFile("pass"));
        Thread.sleep(1000);
        rgScreen.passValidation.sendKeys(generalPage.readFromFile("pass"));
        Thread.sleep(1000);
        rgScreen.clickOnRegistertionButton.click();
        Thread.sleep(1000);

    }

}
