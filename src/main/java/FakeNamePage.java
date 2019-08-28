import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Lucas Porto Gomes.
 */

public class FakeNamePage {

    private WebDriver driver;

    public FakeNamePage(WebDriver driver) {
        this.driver = driver;
    }


    public void acessarFakeNameSite() {
        driver.get("https://www.fakenamegenerator.com");
    }

    public void acessarGoogleSite() {
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public String[] getTextSplit(String classe, String tag) {
        return driver.findElement(By.className(classe)).findElement(By.tagName(tag)).getText().split(" ");
    }

    public void escreve(String id_campo, String texto) {
        driver.findElement(By.id(id_campo)).clear();
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public void escreveXPath(String xpath, String texto) {
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
    }


    public void clicar(String id_campo) {
        driver.findElement(By.id(id_campo)).click();
    }


    public void selecionarCombo(String id_campo, String valor) {

        WebElement element = driver.findElement(By.id(id_campo));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);

    }

}
