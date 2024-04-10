import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Jefferson");
		page.setSobrenome("Farias");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Especializacao");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		assertTrue(page.obterStatusCadastro().startsWith("Cadastrado!"));
		assertTrue(page.obterNomeCadastrado().endsWith("Jefferson"));
		assertEquals("Sobrenome: Farias", page.obterSobrenomeCadastrado());
		assertEquals("Sexo: Masculino", page.obterSexoCadastrado());
		assertEquals("Comida: Pizza", page.obterComidaCadastrado());
		assertEquals("Escolaridade: especializacao", page.obterEscolaridadeCadastrado());
		assertEquals("Esportes: Natacao", page.obterEsporteCadastrado());
	}
	
}
