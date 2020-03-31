
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class loadingScreen {
    WebDriver driver;

    @FindBy(css = "div#app-loading-img div.bounce1")
    WebElement spinner;

    public loadingScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
