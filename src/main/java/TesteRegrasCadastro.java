import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter (value=1)
	public String sobrenome;
	@Parameter (value=2)
	public String sexo;
	@Parameter (value=3)
	public List<String> comidas;
	@Parameter (value=4)
	public String[] esportes;
	@Parameter (value=5)
	public String msg;
	
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"","","", new ArrayList<>(), new String[] {}, "Nome eh obrigatorio"},
			{"Jefferson","","", new ArrayList<>(), new String[] {}, "Sobrenome eh obrigatorio"},
			{"Jefferson","Farias","", new ArrayList<>(), new String[] {}, "Sexo eh obrigatorio"},
			{"Jefferson","Farias","Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Jefferson","Farias","Masculino", Arrays.asList("Carne"), new String[] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}

	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveValidarRegras() {
		
			page.setNome(nome);
			page.setSobrenome(sobrenome);
			
			if(sexo.equals("Masculino"))
				page.setSexoMasculino();
			if(sexo.equals("Feminino")) 
				page.setSexoFeminino();
			
			
			if(comidas.contains("Carne"))
				page.setComidaCarne();
			if(comidas.contains("Frango"))
				page.setComidaFrango();
			if(comidas.contains("Pizza"))
				page.setComidaPizza();
			if(comidas.contains("Vegetariano"))
				page.setComidaVegetariano();
			
			page.setEsporte(esportes);
			page.cadastrar();
			System.out.println(msg);
			assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
	
}
