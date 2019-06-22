package kaveri;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUP_PO {
	
	public WebElement get_Email_Field(WebDriver d)
	{
		WebElement emailField=d.findElement(By.xpath("//input[@id='email']"));
		return emailField;
	}

	public WebElement get_Passward_Field(WebDriver d)
	{
	   WebElement passward=d.findElement(By.xpath("//input[@id='password']"));
		return passward;
	}
	
	public WebElement get_SignIn_Button(WebDriver d)
	{
	   WebElement signINButton =d.findElement(By.xpath("//button[text()='Sign In']"));
		return signINButton	;
	}

	public WebElement get_Register_New_Member_link(WebDriver d)
	{
		 WebElement register_new_member_link=d.findElement(By.xpath("//a[text()='Register a new membership']"));
		return register_new_member_link	;
	}

	public WebElement get_Passward_Field_Validation_Message(WebDriver d)
	{
		WebElement passward__Field_Validation_Message=d.findElement(By.xpath("//div[@id='password_error']"));
		return passward__Field_Validation_Message	;
	}
	
	public WebElement get_Email_Field_Validation_Message(WebDriver d)
	{
		WebElement email__Field_Validation_Message=d.findElement(By.xpath("//div[@id='email_error']"));
		return email__Field_Validation_Message	;
	}
	
	public WebElement get_New_Member_Page_Header(WebDriver d)
	{
		   WebElement new_Member_Page_header=d.findElement(By.xpath("//p[@class='login-box-msg']"));
		  return new_Member_Page_header	;
	}
	
	public WebElement get_I_Already_Have_Membership_Link(WebDriver d)
	{

		   WebElement i_already_have_Membership_Link=d.findElement(By.xpath("//a[text()='I already have a membership']"));
		  return i_already_have_Membership_Link	;
	}
	
	public WebElement get_Dashboard_Header(WebDriver d)
	{
		  WebElement dashboardLabel=d.findElement(By.xpath("//h1"));
		return dashboardLabel	;
	}
}
