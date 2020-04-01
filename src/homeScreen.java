import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homeScreen {
    WebDriver driver;
    private registrtionScreen rgScreen;
    private homeScreen hmscreen;


    @FindBy(css = "ul.nav-bar.buttons li:nth-of-type(3)")
    WebElement loginAndRegister;
    @FindBy(css= "span.text-btn")
    WebElement Register;
    @FindBy(css = "form.form.ember-view div.ember-chosenselect:nth-of-type(1)")
    WebElement pickAprice;
    @FindBy(css = "div.form-control.dib:nth-of-type(1) li:nth-of-type(3)")
    WebElement choosePrise;
    @FindBy(css = "form.form.ember-view div:nth-of-type(2)")
    WebElement pickArea;
   @FindBy(css = "div.form-control.dib:nth-of-type(2) li:nth-of-type(2)")
   WebElement chooseArea;
    @FindBy(css = "form.form.ember-view div:nth-of-type(3)")
    WebElement pickCategory;
    @FindBy(css = "div.form-control.dib:nth-of-type(3) li:nth-of-type(7)")
    WebElement chooseCategory;
    @FindBy(css = ".ui-btn.search.ember-view")
    WebElement clickOnSearchGift;
    @FindBy(css = "li.ember-view.dropdown")
    WebElement myAccount;
    @FindBy(css = ".ui-btn.orange.large")
    WebElement clickOnSubmit;
    @FindBy(css = "li.parsley-required")
    WebElement getTheRedText;
    @FindBy(css=".dropdown-item span") WebElement signOut;
    @FindBy(css="img[src=\"../../../images/close_btn.svg\"]") WebElement exit;

    public homeScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void homeScreenChooseGiftOptions() throws InterruptedException {
        hmscreen = new homeScreen(driver);
        hmscreen.pickAprice.click();
        Thread.sleep(1000);
        hmscreen.choosePrise.click();
        Thread.sleep(1000);
        hmscreen.pickArea.click();
        Thread.sleep(1000);
        hmscreen.chooseArea.click();
        Thread.sleep(1000);
        hmscreen.pickCategory.click();
        Thread.sleep(1000);
        hmscreen.chooseCategory.click();
        Thread.sleep(1000);
        hmscreen.clickOnSearchGift.click();
        Thread.sleep(1000);
    }

    public void loginRedText() throws InterruptedException {
        hmscreen = new homeScreen(driver);
        Actions builder = new Actions(driver);
        WebElement mouseOverMyAccount = myAccount;
        builder.moveToElement(mouseOverMyAccount).perform();
        Thread.sleep(1000);
        signOut.click();
        loginAndRegister.click();
        clickOnSubmit.click();
        Thread.sleep(2000);
    }
}