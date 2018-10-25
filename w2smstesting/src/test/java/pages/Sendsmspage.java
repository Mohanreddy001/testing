package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sendsmspage 
{
public WebDriver driver;
	
	@FindBy(xpath="(//a[@href='send-sms'])[5]")
	public WebElement sendsms;
	
	@FindBy(xpath="(//a[@href='Logout'])[2]")
	public WebElement logout;
	
	//constructor
	public Sendsmspage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//operations
    public void logoutClick()
	{
		logout.click();
	}

}
