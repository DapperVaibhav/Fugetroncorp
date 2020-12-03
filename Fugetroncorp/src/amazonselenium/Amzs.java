package amazonselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Amzs {
	
	WebDriver driver;
	
	
	
	@Test(priority=0)
	public void Create() throws IOException, Exception{
		
		driver.findElement(By.xpath("//span[@class='nav-line-2 nav-long-width']")).click();
		WebDriverWait wait = new WebDriverWait(driver,30); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='createAccountSubmit']")));
        driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
        
        ArrayList<String> name = ReadData(0);
		ArrayList<String> mobile = ReadData(1);
		ArrayList<String> email = ReadData(2);
		ArrayList<String> password = ReadData(3);
		
		for(int i=0;i<name.size();i++)
		{
			driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys(name.get(i));
			driver.findElement(By.xpath("//input[@id='ap_phone_number']")).sendKeys(mobile.get(i));
			driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys(email.get(i));
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys(password.get(i));
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			Thread.sleep(10000);
			
			if(driver.findElement(By.linkText("Sign-In")).isDisplayed()) {
				   System.out.println("Account already Exist");
				   driver.findElement(By.linkText("Create account with a different mobile number")).click();
			   }
			
				   System.out.println("New Account");
			 
		}
		   
	}
	
       public ArrayList<String> ReadData(int colno) throws IOException, Exception{		
		ArrayList<String> list=new ArrayList<String>();
		File src=new File("D:\\Test.xlsx");		  
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet= workbook.getSheet("Sheet1");
		Iterator<Row> row= sheet.iterator();
		row.next();
		while(row.hasNext())
		{
			list.add(row.next().getCell(colno).getStringCellValue().toString());
		}
		return list;
       }
	
	@BeforeTest
	  public void beforeTest() throws InterruptedException {
		    System.setProperty("webdriver.chrome.driver", "D:\\Chrome\\chromedriver_win32 (1)\\chromedriver.exe");
			driver=new ChromeDriver();
		    driver.get("https://www.amazon.in");
		    driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver,30); 
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-logo']")));
		  
	  }

	  @AfterTest
	  
	  public void afterTest() {
		  driver.close();
	  }

}
