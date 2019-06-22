package kaveri;
//mport AllTestPOFiles.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUPTest {
	
	WebDriver d=null;
	String offline_WebSite_URL="file:///F:/soft/Selenium%20Software/Offline%20Website/index.html";
	String browserChoice;
	
	SignUP_PO signUp;
	
	@Parameters("browser")
	@BeforeTest
	public void setupEnviornment(@Optional("FireFox") String browser) throws Exception
	{
		System.out.println("browser = "+browser);
		 browserChoice=browser;
		System.out.println("Browser Choice Read from Config File = "+browserChoice);
		
		if(browserChoice.equalsIgnoreCase("Chrome"))
		{
			
			System.out.println("Launching Chrome Browser");
					
			try {
				System.out.println(" Executing on CHROME");
				//set desired capablities
				DesiredCapabilities cap =DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				
				//
				String Node = "http://localhost:5557/wd/hub";
				d = new RemoteWebDriver(new URL(Node), cap);
				d.manage().timeouts().implicitlyWait(10,
				TimeUnit.SECONDS);
				// Launch web site

				d.get(offline_WebSite_URL);
				d.manage().window().maximize();

				} catch (Exception e) 
				{
				e.printStackTrace();
				}
				
		}
		else if(browserChoice.equalsIgnoreCase("FireFox"))
		{
			System.out.println(" Executing on FireFox");
			String Node = "http://localhost:5555/wd/hub";
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			d = new RemoteWebDriver(new URL(Node), cap);
			
			d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			// Launch web site

			d.get(offline_WebSite_URL);
			d.manage().window().maximize();
			
			
		}
		else if(browserChoice.equalsIgnoreCase("IE"))
		{
			System.out.println("code for IE");
		}
		else{
			System.out.println("Browser Launch Configuration is not defined for Config>>Browser = "+browserChoice);
		}
		
		signUp= new SignUP_PO(); //creating po object
	}
	
	@BeforeMethod
	public void clearFields()
	{
		 signUp.get_Email_Field(d).clear();
		 signUp.get_Passward_Field(d).clear();
		 
	}
	
	@Test(priority=1 , description="Validation check for empty Email field" , enabled=true)
	public void validation_For_Empty_Email() {
		   
		System.out.println("Title = "+d.getTitle());
		
		signUp.get_SignIn_Button(d).click(); //click on sign up
	
		//enter email validation should received
		Assert.assertEquals(signUp.get_Email_Field_Validation_Message(d).getText(),"Please enter email.", "Please enter email. msg not received ");
		  
	}

	@Test(priority=2 , description="Validation check for empty Passward field" , enabled=true)
	public void validation_For_Empty_Passward() {
		  

			System.out.println("Title = "+d.getTitle());
			
			signUp.get_SignIn_Button(d).click(); //click on sign up
		   
		   Assert.assertEquals(signUp.get_Passward_Field_Validation_Message(d).getText(),"Please enter password.", "Please enter password. msg not recived");
		   	   
		   
	  }
	  
	@Test(priority=3 , description="Validation check when Email other than kiran@gmail.com provided" , enabled=true)
	  public void validation_For_incorrect_admin_Email() {
		  
		   System.out.println("Title = "+d.getTitle());
		   
		   signUp.get_Email_Field(d).sendKeys("kaveri@gmail.com");
		   
		   signUp.get_SignIn_Button(d).click();
		   
		   Assert.assertEquals(signUp.get_Email_Field_Validation_Message(d).getText(),"Please enter email as kiran@gmail.com", "Please enter email as kiran@gmail.com msg not received ");
		  	   
		   
	  }
	  
	  @Test(priority=4 , description="Validation check when Email format is not validd" , enabled=true)
	  public void validation_For_invalid_Email_Format() {
		  
		   System.out.println("Title = "+d.getTitle());
		   
		   signUp.get_Email_Field(d).sendKeys("kaveri123@");
		   
		   signUp.get_SignIn_Button(d).click();		   
		   
		   Assert.assertEquals(signUp.get_Email_Field_Validation_Message(d).getText() ,"Please enter valid email.", "Please enter valid email. msg not received ");
		  	   		   
	  }

	  @Test(priority=5 , description="Validation check when passward other than 123456 is provided" , enabled=true)
	  public void validation_For_incorrect_admin_passward() {
		  
		  System.out.println("Title = "+d.getTitle());
		 	
		   signUp.get_Email_Field(d).sendKeys("kiran@gmail.com");
		   
		   signUp.get_Passward_Field(d).sendKeys("12");
		   
		   signUp.get_SignIn_Button(d).click();
		   
		   Assert.assertEquals( signUp.get_Passward_Field_Validation_Message(d).getText(),"Please enter password 123456","Please enter password 123456. msg not recived");
		   	   
	  }

	  @Test(priority=6 , description="Verify Clicking on Register New Member navigate to Register a new membership page" ,enabled=true)
	  public void register_New_Member_Link_Navigation() {
		  System.out.println("Title = "+d.getTitle());
		
		   signUp.get_Register_New_Member_link(d).click();
		   
		  Assert.assertEquals(signUp.get_New_Member_Page_Header(d).getText(),"Register a new membership","Register a new membership message not recevied . user not navigate to new member page");
		   	   	   
	  }
	  
	  @Test(priority=7 , description="Verify Clicking on I already have a membership navigate to signin page" ,enabled=true)
	  public void i_already_have_a_membership_Link_Navigation() {
		  System.out.println("Title = "+d.getTitle());
		 	
		  signUp.get_I_Already_Have_Membership_Link(d).click();
		  Assert.assertEquals(signUp.get_New_Member_Page_Header(d).getText(),"Sign in to start your session","Sign in to start your session message not recevied . user not navigate to sign in page");
		   	   	   
	  }
	  
	  @Test(priority=8 , description="verification For Email field Placeholder" , enabled=true)
	  public void verification_For_Email_field_Placeholder() {
		  
		   System.out.println("Title = "+d.getTitle());
		  
		   String place_Holder_For_email =signUp.get_Email_Field(d).getAttribute("placeholder");
		    Assert.assertEquals(place_Holder_For_email,"Email", "placeholder-Email is not recived for email field");
		    
	  }
	  
	  @Test(priority=9 , description="verification For Passward field Placeholder" , enabled=true)
	  public void verification_For_passward_field_Placeholder() {
		   System.out.println("Title = "+d.getTitle());
		  
		   String place_Holder_For_passward =signUp.get_Passward_Field(d).getAttribute("placeholder");
		   Assert.assertEquals(place_Holder_For_passward,"Password", "placeholder-Password is not recived for passward field");
		    
		    
	  }
	
	  @Test(priority=10 , description="Successful login when valid creditional provided" ,enabled=true)
	  public void login_with_valid_creditional() {
		
		   System.out.println("Title = "+d.getTitle());
		 	
		   signUp.get_Email_Field(d).sendKeys("kiran@gmail.com");
		   
		   signUp.get_Passward_Field(d).sendKeys("123456");
		   
		   signUp.get_SignIn_Button(d).click();		   
		   
		   Assert.assertEquals( signUp.get_Dashboard_Header(d).getText(),"Dashboard Control panel","User login is not successful");
		   	   
		   
	  }
	  @AfterTest
	  public void closeBrowser()
	  {
		  d.close();
		  d.quit();
	  }
}
