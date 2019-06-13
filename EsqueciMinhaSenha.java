package Dircec;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EsqueciMinhaSenha {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mateo\\OneDrive\\Documentos\\Automacao\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://arealogada.caixaseguradora.com.br/login");
		
		driver.findElement(By.linkText("Esqueci minha senha")).click();
		
		driver.findElement(By.cssSelector("div.form-group active")).sendKeys("530.613.750-40");	
		driver.findElement(By.cssSelector("span.E-Mail"));
		driver.findElement(By.cssSelector("button.sc-kkGfuU cKnqUZ sc-kEYyzF jeYUyz"));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
