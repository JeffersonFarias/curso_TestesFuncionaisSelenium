import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL (driver);
	}
	
	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicarCheck("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicarCheck("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String escolaridade) {
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
	}
	
	public void setEsporte(String... esporte) {
		for (String escolha:esporte)
			dsl.selecionarCombo("elementosForm:esportes", escolha);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterStatusCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastrado() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastrado() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastrado() {
		return dsl.obterTexto("descSexo");
	}
	public String obterComidaCadastrado() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastrado() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterEsporteCadastrado() {
		return dsl.obterTexto("descEsportes");
	}
}
