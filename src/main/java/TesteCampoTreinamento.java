import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
//	@After
//	public void finaliza(){
//		driver.quit();
//	}
	
	@Test
	public void testeTextField(){
		page.setNome("Teste de escrita");;
		assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo(){
		page.setNome("Jefferson");
		assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		page.setSobrenome("Farias");
		assertEquals("Farias", dsl.obterValorCampo("elementosForm:sobrenome"));
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		page.setSexoMasculino();
		assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		page.setComidaPizza();
		assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveIntegarirComCombo(){
		page.setEscolaridade("Especializacao");
		assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		page.setEsporte("Natacao");
		page.setEsporte("Corrida");
		page.setEsporte("O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		assertEquals(2, opcoesMarcadas.size());
		assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		dsl.clicarBotao("buttonSimple");
		assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		dsl.clicarLink("Voltar");
		
		assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
		assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
	}
	
	
}


