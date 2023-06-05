package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rDriver)
	{
		ldriver=rDriver;

		PageFactory.initElements(rDriver, this);
	}
	
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;

	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	WebElement LoginBtn;

	@FindBy(xpath = "//h1[contains(text(),'Sign in')]")
	WebElement SigninPage;
	
	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;
	
	public void enterEmail(String emailAdd)
	{
		username.clear();
		username.sendKeys(emailAdd);
	}
	
	public void enterPassword(String pwd)
	{
		password.clear();
		password.sendKeys(pwd);
	}
	
	public  void waitForPageLoad() {

		ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(ldriver, ofSeconds(10));
		try {
			wait.until(expect);
		} catch (Exception E) {
			E.printStackTrace();
		}
	}
	
	public String SigninPage_Validation(String signintxt) {
		waitForPageLoad();
		if(SigninPage.getText().equals(signintxt))
		{
			return "PageLoaded Success";
		}
		else
		{
			return "PageLoaded Failed";
		}
	}

	
	public void clickOnLoginButton()
	{
		LoginBtn.click();
	}
	
	public void clickOnLogOutButton()
	{
		logout.click();
	}
}
