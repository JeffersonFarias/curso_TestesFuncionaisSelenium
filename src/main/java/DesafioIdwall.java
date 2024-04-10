import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DesafioIdwall {
		
		
		private WebDriver driver;
		private DSL dsl;

		@Before
		public void inicializa(){
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1200, 765));
			driver.get("https://blog.idwall.co/");
			dsl = new DSL(driver);
		}
		
		@Test
		public void validaPesquisaPost() throws InterruptedException {
			
			String tituloPost= "Segurança inteligente: como unir tecnologia e estratégia de gestão de identidade";

			
			Thread.sleep(2000);
			driver.findElement(By.className("trx_addons_icon-search")).click();
			
			driver.findElement(By.className("search_field")).sendKeys(tituloPost);

			driver.findElement(By.className("search_field")).sendKeys(Keys.ENTER);
			
			String link = driver.findElement(By.linkText(tituloPost)).getText();
			
			System.out.println(driver.findElement(By.linkText(tituloPost)));
			
			assertEquals(tituloPost, link);
			
		}
		

}
