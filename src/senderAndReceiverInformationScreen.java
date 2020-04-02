import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class senderAndReceiverInformationScreen {
    WebDriver driver;
    String mypath="C:\\Users\\Alon\\Desktop\\photo.png";
    private senderAndReceiverInformationScreen senderAndRecieverInfoScreen;


    @FindBy(css="label.option.selected")  WebElement myRadioForSomeOneElse;
    @FindBy(css="label.send-now") WebElement afterPaymentSendGift;
    @FindBy(css="label.ember-view.ui-field.ui-input:nth-of-type(1)") WebElement giftForWho;
    @FindBy(css="input[data-parsley-required-message=\"למי יגידו תודה? שכחת למלא את השם שלך\"]") WebElement fromWhoTheGift;
    @FindBy(css="textarea[rows=\"4\"]") WebElement textBlessing;
    @FindBy(css="form.ember-view div.mx2.md1 div.ember-chosenselect:nth-of-type(1)")
    WebElement whichEvent;
    @FindBy(css="form.ember-view div.mx2.md1 div.ember-chosenselect:nth-of-type(1) ul li:nth-of-type(4)")
    WebElement chooseTheEvent;
    @FindBy(css="button.btn.btn-clean.btn-send-option.fluid span.icon.icon-envelope") WebElement emailSendGift;
    @FindBy(css="input[placeholder=\"כתובת המייל של מקבל/ת המתנה\"]") WebElement placeHolder;
    @FindBy(css="button.btn.btn-theme.btn-save") WebElement saveEmail;
    @FindBy(css="button.ui-btn.orange.large ") WebElement SendGift;
    @FindBy(css="input[name=\"fileUpload\"]") WebElement uploadFile;
    @FindBy(css="div.form-well") WebElement VerifyPaymentOption;
    @FindBy(css=".step-title.highlighted") WebElement colorElement;
    @FindBy(css="div.receiver span.name") WebElement receiver;
    @FindBy(css="div.sender span.name") WebElement sender;
    @FindBy(css="p.card-text.cut-greeting") WebElement blessing;


    public senderAndReceiverInformationScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void mytest04Actions() throws Exception {
        senderAndRecieverInfoScreen=new senderAndReceiverInformationScreen(driver);
        boolean selected_value=senderAndRecieverInfoScreen.myRadioForSomeOneElse.isSelected();
        if(!selected_value)
            senderAndRecieverInfoScreen.myRadioForSomeOneElse.click();
        giftForWho.click();
        giftForWho.sendKeys(generalPage.readFromFile("receiver"));
        fromWhoTheGift.clear();
       fromWhoTheGift.sendKeys(generalPage.readFromFile("fromWhoGift"));
        whichEvent.click();
        chooseTheEvent.click();
        textBlessing.clear();
        textBlessing.sendKeys(generalPage.readFromFile("blessing"));
        WebElement uploadAFile = uploadFile;
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadAFile);
       Thread.sleep(5000);
        generalPage.uploadAFile(uploadFile,mypath);
        boolean selectedValue=afterPaymentSendGift.isSelected();
        if(!selectedValue)
            afterPaymentSendGift.click();
        emailSendGift.click();
        placeHolder.click();
        placeHolder.sendKeys(generalPage.readFromFile("placeHolder"));
        saveEmail.click();
        SendGift.click();
        Thread.sleep(5000);
    }

}
