package tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.Homepage;
import pages.Sendsmspage;
public class Gluecode 
{
	public WebDriver driver;
	public WebDriverWait wait;
	public Homepage hp;
	public Sendsmspage sp;
	public Scenario s;
	public Properties pro;
    
	@Before
	public void method(Scenario s) throws Exception
	{
		//use scenorio object for current scenorio
		this.s=s;
		//load properties file for current scenorio
		pro=new Properties();
		FileInputStream fip=new FileInputStream("D:\\batch239\\w2smstesting\\src\\test\\resources\\mypack\\w2smsproperties.properties");
		pro.load(fip);
	}
	
	@Given("^launch site using \"(.*)\"$")
	public void method2(String b)
	{
		//open browser for current scenorio
		if(b.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",pro.getProperty("cdriver"));
			driver=new ChromeDriver();
		}
		else if(b.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",pro.getProperty("ffdriver"));
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.ie.driver",pro.getProperty("iedriver"));
			driver=new InternetExplorerDriver();
		}
		//create objects for page classes for current scenorio
		hp=new Homepage(driver);
		sp=new Sendsmspage(driver);
		//open site
		driver.get(pro.getProperty("url"));
		//defaine wait objects
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		driver.manage().window().maximize();
	}
	
	@Then("^title contains \"(.*)\"$")
	public void method3(String a) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		String t=driver.getTitle();
		if(t.contains(a))
		{
			s.write("title test passed");
		}
		else
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,"title test failed");
			Assert.fail();
		}
	}
	
	@And("^close site$")
	public void method4()
	{
		driver.close();
	}
	
	@When("^enter mobile number as \"(.*)\"$")
	public void method5(String u)
	{
		wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		hp.fillmbno(u);
	}
	
	@And("^enter password as \"(.*)\"$")
	public void method6(String p)
	{
		wait.until(ExpectedConditions.visibilityOf(hp.pwd));
		hp.fillpwd(p);
	}
	
	@And("^click login$")
	public void method7()
	{
		wait.until(ExpectedConditions.visibilityOf(hp.signin));
		hp.clickSignin();
	}
	
	@Then("^validate output for critiria \"(.*)\"$")
	public void method8(String c) throws Exception
	{
		//wait.until(temp->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
		Thread.sleep(5000);
		try
		{
			if(c.equals("all_valid") && sp.sendsms.isDisplayed())
			{
				s.write("test passed for valid data");
			}
			else if(c.equals("mbno_blank") && hp.mbno_blank_err.isDisplayed())
			{
				s.write("test passed for blank mbno");
			}
			else if(c.equals("pwd_blank") && hp.pwd_blank_err.isDisplayed())
			{
				s.write("test passed for blank pwd");
			}
			else if(c.equals("mbno_invalid") && hp.mbno_invalid_err.isDisplayed())
			{
				s.write("test passed for invalid mbno");
			}
			else if(c.equals("pwd_invalid") && hp.pwd_invalid_err.isDisplayed())
			{
				s.write("test passed for invalid pwd");
			}
			else
			{
				byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				s.embed(ssbytes,"login test failed");
				Assert.fail();
			}	
		}
		catch(Exception ex)
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,ex.getMessage());
		}	
	}
	
	@When("^do login with valid data$")
	public void method9(DataTable dt)
	{
		List<List<String>> l=dt.asLists(String.class);
		hp.fillmbno(l.get(1).get(0));
		hp.fillpwd(l.get(1).get(1));
		hp.clickSignin();
	}
	
	@And("^do logout$")
	public void method10() throws Exception
	{
		Thread.sleep(10000);
		sp.logoutClick();
	}
	
	@Then("^home page will be reopened$")
	public void method11()
	{
		if(hp.mbno.isDisplayed())
		{
			s.write("logout successfully");
		}
		else
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes,"unsuccessful logout");
			Assert.fail();
		}
	}

}
