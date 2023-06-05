package PageObject;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewCustomerPage {

	public WebDriver ldriver;

	//constructor
	public AddNewCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}

	//Find web elements on the web page
	@FindBy(xpath="//a[@href='#']//p[contains(text(),'Customers')]")	 
	WebElement lnkCustomers_menu;


	@FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	WebElement lnkCustomers_menuitem;
	
	@FindBy(xpath="//span[contains(text(),' New')]")
	WebElement Cypress_Newoption;

	@FindBy(xpath = "//a[@class='btn btn-primary']")	 
	WebElement btnAddnew;
	
	@FindBy(xpath = "//span[contains(text(),'Bank Accounts')]")	 
	WebElement BankAccountCreationWindow;
	
	@FindBy(xpath = "//span[contains(text(),'Create')]")	 
	WebElement BankAccountCreationCreate;
	
	@FindBy(id = "bankaccount-bankName-input")
	WebElement BankName;
	@FindBy(id = "bankaccount-routingNumber-input")
	WebElement Routnumber;
	@FindBy(id = "bankaccount-accountNumber-input")
	WebElement AccountNumber;
	

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement Cypress_save;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement txtEmail;


	@FindBy(xpath = "//input[@id='Password']")
	WebElement txtPassword;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	WebElement txtCustomerRoles;


	@FindBy(xpath = "//li[contains(text(),'Administrators')]")
	WebElement listItemAdministrators;


	@FindBy(xpath = "//li[contains(text(),'Registered')]")
	WebElement listItemRegistered;

	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	WebElement listItemGuests;


	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	WebElement listItemVendors;
	//VendorId

	@FindBy(xpath = "//*[@id='VendorId']")
	WebElement dropdownVendorMgr;

	@FindBy(id = "Gender_Male")
	WebElement MaleGender;


	@FindBy(id = "Gender_Female")
	WebElement FeMaleGender;


	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement txtFirstName;


	@FindBy(xpath = "//input[@id='LastName']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	WebElement txtDob;

	@FindBy(xpath = "//input[@id='Company']")
	WebElement txtCompanyName;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	WebElement txtAdminContent;

	@FindBy(xpath = "//button[@name='save']")
	WebElement btnSave;
	
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
	//Actions Methods for web elements

	public String getPageTitle()
	{
		return ldriver.getTitle();
	}

	public void clickOnCustomersMenu() {
		lnkCustomers_menu.click();
	}

	public void clickOnCustomersMenuItem() {
		lnkCustomers_menuitem.click();
	}
	
	public void clickOnNewTransaction() throws InterruptedException {
		waitForPageLoad();
		Thread.sleep(3000);
		Cypress_Newoption.click();
	}

	public void gotoBankAccountCreationWindow() {
		BankAccountCreationWindow.click();
	}
	public void Bank_Account_Creation_Window() {
		BankAccountCreationWindow.click();
		BankAccountCreationCreate.click();
	}
	
	public void User_enters_Bank_Name(String Bname) {
		BankName.sendKeys(Bname);
	}
	
	public void User_enters_Routing_Number(String RoutNumber) {
		Routnumber.sendKeys(RoutNumber);
	}

	public void User_enters_Account_Number(String Accnumber) {
		AccountNumber.sendKeys(Accnumber);
	}
	
	public void User_click_on_Save() {
		Cypress_save.click();
	}
	
	public void clickOnAddnew() {
		btnAddnew.click();
	}
	
	public void enterEmail(String email)
	{
		txtEmail.sendKeys(email);
	}

	public void enterPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	public void enterFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}

	public void enterDob(String dob)
	{
		txtDob.sendKeys(dob);
	}

	public void enterCompanyName(String coName)
	{
		txtCompanyName.sendKeys(coName);
	}

	public void enterAdminContent(String content)
	{
		txtAdminContent.sendKeys(content);
	}

	/*public void enterCustomerRoles(String role)  
	{

	}*/

	public void enterManagerOfVendor(String value)
	{
		Select drp=new Select(dropdownVendorMgr);
		drp.selectByVisibleText(value);
	}

	public void enterGender(String gender)
	{
		if(gender.equals("Male"))
		{
			MaleGender.click();
		}
		else if(gender.equals("Female"))
		{
			FeMaleGender.click();
		}
		else//default set Male gender
		{
			MaleGender.click();
		}

	}

	

	public void clickOnSave()
	{
		btnSave.click();
	}

}
