package Dircec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;


public class Login {
	
	//Instanciando a classe
	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String cpf = "";
		
		//Autenticação no proxy
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mateo\\OneDrive\\Documentos\\Automacao\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://arealogadadev.caixaseguradora.com.br/login");		
		
		//Imput dos dados de login
		imputDados (cpf);
    	System.out.println(cpf);
		
		driver.findElement(By.className("class.form-group active")).sendKeys(cpf);
    	System.out.println("asdfasdfa");

		driver.findElement(By.id("password")).sendKeys("Caixa123!");
		
		driver.findElement(By.cssSelector("class.sc-kkGfuU fJxzP sc-kEYyzF dHssbM")).click();
		
		
		//Identifica a falha 
		//WebElement loginSucesso = driver.findElement(By.className("loginForm"));
		//String textoErroLogin = loginSucesso.getText();
		//assertEquals("Dados de acesso não conferem.", textoErroLogin);
		
		driver.quit();
		  
	}

	private static void imputDados(String cpf) throws IOException {
		// TODO Auto-generated method stub
		//Scanner ler = new Scanner(System.in);
		
	    String nome = "C:\\Users\\mateo\\OneDrive\\Documentos\\Automacao\\DadosLogin.txt";

	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);

	      String linha = lerArq.readLine(); 
		// lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo de repetição atingir o final do arquivo texto
	      while (linha != null) {
	        System.out.printf("%s\n", linha);

	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }

	      arq.close();
	    } catch (IOException e) {
	          e.getMessage();
	          System.out.print(e);
	    }
	    System.out.println();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	


	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
