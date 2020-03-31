import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class generalPage {
    static WebDriver driver;
    private static senderAndReceiverInformationScreen senderAndRecieverInfoScreen;
    public generalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String readFromFile(String keyData) throws Exception {
        File xmlFile = new File("C://Users//Alon//Desktop//myConfigFile.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyData).item(0).getTextContent();
    }

    public static void uploadAFile(WebElement element, String mypath){
        element.sendKeys(mypath);
    }
}
