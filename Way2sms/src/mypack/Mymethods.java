package mypack;

	import java.io.File;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Mymethods 
	{
		public WebDriver driver;
		public WebDriverWait Wait;
		public String launch(String e,String d,String c) throws Exception
		{
			if(e.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","E:\\mindq\\chromedriver.exe");
				driver=new ChromeDriver();
			}
			else 
			{
				return("Unknown Browser");
			}
			driver.get(d);
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile No")));
			driver.manage().window().maximize();
			return("Done");
		}
		public String fill(String e,String d,String c) throws Exception
		{
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
			driver.findElement(By.xpath(e)).sendKeys(d);
			return("Done");
		}
		public String click(String e,String d,String c) throws Exception
		{
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
			driver.findElement(By.xpath(e)).click();
			return("Done");
		}
		public String validatelogin(String e,String d,String c) throws Exception
		{
			Thread.sleep(5000);
			try
			{
				if(c.equalsIgnoreCase("all_valid") && driver.findElement(By.xpath("(//*[@name='mobileNo'])")).isDisplayed())
				{
					return("passed");
				}
				else if(c.equalsIgnoreCase("mbno_blank") && driver.findElement(By.xpath("//*[text()='Enter your mobile number']")).isDisplayed())
				{
					return("passed");
				}
				else if(c.equalsIgnoreCase("mbno_wrong_size") && driver.findElement(By.xpath("//*[text()='Enter valid mobile number']")).isDisplayed())
				{
					return("passed");
				}
				else if(c.equalsIgnoreCase("mbno_invalid") && driver.findElement(By.xpath("(//*[text()='Enter password'])[2]")).isDisplayed())
				{
					return("passed");
				}
				else if(c.equalsIgnoreCase("pwd_blank") && driver.findElement(By.xpath("(//*[contains(text(),'not register with us')])[1]")).isDisplayed())
				{
					return("passed");
				}
				else if(c.equalsIgnoreCase("pwd_invalid") && driver.findElement(By.xpath("(//*[contains(text(),'Try Again')])[1]")).isDisplayed())
				{
					return("passed");
				}
				else
				{
					String temp=this.screenshot();
					return("Test Failed and goto"+temp);
				}
			}
			catch(Exception ex)
			{
				String temp=this.screenshot();
				return("Test interrupted and goto"+temp);
			}
		}
		public String closesite(String e,String d,String c) throws Exception
		{
			driver.close();
			return("Done");
		}
		public String screenshot() throws Exception
		{
			Date d=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
			String ssname=sf.format(d)+".png";
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dest=new File(ssname);
			FileHandler.copy(src,dest);
			return(ssname);	
		}
		}
