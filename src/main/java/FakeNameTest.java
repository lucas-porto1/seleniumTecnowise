import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Avaliação da empresa Procondutor (Grupo tecnowise).
 * @author Lucas Porto Gomes.
 */


public class FakeNameTest {

    private WebDriver driver;
    private FakeNamePage page;


    @Before
    public void inicializa() {
        driver = new FirefoxDriver(); // Apontando o driver para o firefox.
        driver.manage().window().maximize(); // Alterando tamanho da janela do navegador.
        page = new FakeNamePage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void dadosFakeTest() {

        /* Gera dados falsos. */
        page.acessarFakeNameSite(); // acessa o endereço FakeNameGenerator.
        page.selecionarCombo("gen", "Male"); // Seleciona o genero.
        page.selecionarCombo("n", "Brazil"); // Seleciona a nacionalidade
        page.selecionarCombo("c", "Brazil"); // Seleciona o país
        page.clicar("genbtn"); // Gera os dados.

        /* Armazena os dados falsos */
        String[] nome = page.getTextSplit("address", ("h3")); // Armazena o nome do usuário
        String celular = page.getText(By.xpath("//dl[4]/dd[1]")); // Armazena o celular do usuário.

        /* Acessa o site do google para realizar o cadastro.*/
        page.acessarGoogleSite(); // acessa o endereço Google.
        page.escreve("firstName", nome[0]); // Preenche o nome.
        page.escreve("lastName", nome[1] + " " + nome[2]); // Preenche o sobrenome.
        page.escreve("username", nome[1] + nome[2] + "777lpg"); // Preenche o e-mail.
        page.escreveXPath("//input[@name='Passwd']", nome[1] + "condutor10"); // Preenche a senha.
        page.escreveXPath("//input[@name='ConfirmPasswd']", nome[1] + "condutor10"); // Preenche 'confirmar a senha'.
        page.clicar("accountDetailsNext"); // Clica em próximo.
        page.escreve("phoneNumberId", celular + "00"); // Preenche o número de celular inválido.
        page.clicar("gradsIdvPhoneNext"); // Clica em próximo.
        String texto = page.getText(By.xpath("//div[@class='dEOOab RxsGPe']")); //Valida texto do google.

        /* Valida mensagem de erro. */
        Assert.assertEquals("Este formato de número de telefone não é válido. Verifique o país e o número.", texto);

    }


}
