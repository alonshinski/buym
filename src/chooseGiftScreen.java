import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class chooseGiftScreen {
    WebDriver driver;
    @FindBy(css = "img[src=\"https://buyme.co.il/files/siteNewLogo27533.jpg?1489585332546\"]")
    WebElement pickABuisness;
    @FindBy(css="img[src=\"https://buyme.co.il/files/packages/private/packageImage339617.jpg?1573934439\"]")
    WebElement chooseABuisness;
    @FindBy(css="a[href=\"https://buyme.co.il/categories/הכי נמכרים\"]") WebElement scrollToButton;
    @FindBy(css="h1.page-title") WebElement elementOfSearch;
    public chooseGiftScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void scrollInToElement() throws InterruptedException {
        WebElement ele = pickABuisness;
      /* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);*/
       JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
        pickABuisness.click();
        Thread.sleep(1000);
    }

    public void ScrollTobButtonOfthePageExtras(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollToButton);
    }
}
