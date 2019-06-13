package Dircec;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Cadastro {

	private static FileWriter arquivo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mateo\\OneDrive\\Documentos\\Automacao\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		int contador;
		int contadorEmail = 0;

		
		//Não alterar a variável
		for (contador = 0; contador < 10; contador ++) {
			contador++;
			
			//Gerador de massa
			driver.get("https://www.4devs.com.br/gerador_de_cpf");
			
			//Escolher a pontuação ou não do cpf gerado
			//driver.findElement(By.id("pontuacao_nao")).click();
			driver.findElement(By.id("bt_gerar_cpf")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.clipboard-copy")));		
			String cpf;
			cpf = driver.findElement(By.id("texto_cpf")).getText();	
			
			//Visualizar resgate do cpf para exportação
			System.out.println(cpf);
			
			//Gravar cpf no arquivo .txt
			if (contador == 0)
				novoArq(cpf);
			else
				gravaArq(cpf);
			
			
			
			driver.get("https://arealogadaqa.caixaseguradora.com.br/perfil/criar");	
			driver.findElement(By.id("addProfile-CodIdentificador")).sendKeys(cpf);
			
			//WebDriverWait wait3 = new WebDriverWait(driver, 3);
			//wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.clipboard-copy")));	
			
			String nomeUsuario = "Teste Automatizado Caixa Seguros";
			driver.findElement(By.id("addProfile-NomUsuario")).sendKeys(nomeUsuario);
			
			//Incrementação do email
			String email= "marcos.paes0" + contadorEmail + "@rsinet.com.br";
			contadorEmail ++;
			
			
			//Gerar email para validação
			driver.findElement(By.id("addProfile-DesEmailUsuario")).sendKeys(email);
			
			//driver.findElement(By.className("email")).getText();
			
			driver.findElement(By.id("addProfile-NumTelCelularUsuario")).sendKeys("61999999999");
			
			driver.findElement(By.id("addProfile-PassUsuario")).sendKeys("Caixa123!");
			
			driver.findElement(By.id("confirmPassword")).sendKeys("Caixa123!");
			
			
			//Ajuste para as máscaras de cpf e email
			//driver.findElement(By.className("class.profileCreate-rules"));	
			driver.findElement(By.cssSelector("div.card-action")).click();
			
			
			
			//Informa falha se o cadastro não for realizado
			driver.findElement(By.cssSelector("class.validation-error"));
			
			
		}	
		
		
		//Fecha o navegador para requisições
		WebDriverWait wait2 = new WebDriverWait(driver, 1);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.clipboard-copy")));	
		driver.quit();		
	}
	


	public static void novoArq(String cpf) {
        try {
            
            // Cria arquivo
            File file = new File("cadastroAreaLogada.txt");

            // Se o arquivo nao existir, ele gera
            if (!file.exists()) {
                file.createNewFile();
            }

            // Prepara para escrever no arquivo
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Escreve e fecha arquivo
            bw.write(cpf);
            bw.close();
            
           

        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	
	
    private static void gravaArq(String cpf) {
    	// TODO Auto-generated method stub
    	BufferedWriter bw = null;
    	
        try {
            bw = new BufferedWriter(new FileWriter("cadastroAreaLogada.txt", true));
            bw.write(cpf + '\n');
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    	
    }	
    
	


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
