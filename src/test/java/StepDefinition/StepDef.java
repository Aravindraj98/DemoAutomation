package StepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utitlities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
/*Child class of Baseclass*/
public class StepDef extends BaseClass {

	@Before
	public void setup1()
	{
		readConfig = new ReadConfig();
		
		//initialise logger
		log = LogManager.getLogger("StepDef");

		System.out.println("Setup-Sanity method executed..");

		String browser = readConfig.getBrowser();
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

	
		log.fatal("Setup1 executed...");


	}

	/*@Before("@regression")
	public void setup2()
	{
		System.out.println("Setup2-Regression method executed..");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("Setup2 executed...");
	}*/

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		loginPg= new LoginPage(driver);
		addNewCustPg = new AddNewCustomerPage(driver);
		SearchCustPg = new SearchCustomerPage(driver);

		log.info("chrome browser launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		log.info("url opened");

	}
	
	@When("User verifies URL page-loaded {string} success")
	public void user_verfies_URL_pageloaded(String pageTxt) {
		String msg=loginPg.SigninPage_Validation(pageTxt);
		
		System.out.println("MSG:"+msg);
		if (msg.equals("PageLoaded Success")) {
			log.info("Cypress Real World App Loaded Success");
			Assert.assertTrue(true);//pass
		}else{
			log.info("Cypress Real World App Loaded Failed");
			Assert.assertTrue(false);//Fail
		}
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);
		log.info("email address and password entered");

	}

	@When("Click on Login")
	public void click_on_login() {
		loginPg.clickOnLoginButton();

		log.info("Clicked on login button");

	}

	//////////Login feature///////////////////////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle=driver.getTitle();

		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");

			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test Failed: Login feature- page title not matched.");


		}


	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPg.clickOnLogOutButton();
		log.info("user clicked on logout link.");

	}

	/*@Then("close browser")
	public void close_browser() {
		driver.close();
		log.info("Browser closed");

		//driver.quit();
	}*/


	///////////////////////////Add new customer/////////////////////
	
	
	@And("User navigates to Bank Account Creation Window")
	public void User_navigates_to_Bank_Account_Creation_Window() throws InterruptedException {
		Thread.sleep(2000);
		addNewCustPg.Bank_Account_Creation_Window();
		log.info("clicked on Bank Account Creation");

	}
	
	@And("User enters Routing Number as {string}")
	public void User_enters_Routing_Number(String RoutNum) {
		addNewCustPg.User_enters_Routing_Number(RoutNum);
		log.info("clicked on add new button.");

	}
	
	@And("User enters Bank Name as {string}")
	public void User_enters_Bank_Name(String BnkNme) throws InterruptedException {
		Thread.sleep(1500);
		addNewCustPg.User_enters_Bank_Name(BnkNme);
		log.info("Entered Bank Name");
	}
	
	
	
	@And("Click on Save")
	public void Click_on_Save() {
		addNewCustPg.User_click_on_Save();
		log.info("clicked on save button.");

	}
	
	@And("User enters Account Number as {string}")
	public void User_enters_Account_Number(String AccNum) {
		addNewCustPg.User_enters_Account_Number(AccNum);
		log.info("clicked on add new button.");

	}
	

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Cypress Real World App";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view dashboard test passed.");
			Assert.assertTrue(true);

		}
		else
		{
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed.");

		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustPg.clickOnCustomersMenu();
		log.info("New Transfer clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("User click on New Transaction")
	public void User_click_on_New_Transaction() throws InterruptedException {
		addNewCustPg.clickOnNewTransaction();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addNewCustPg.clickOnCustomersMenuItem();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
		log.info("clicked on add new button.");

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("User can view Add new customer page- passed");

			Assert.assertTrue(true);//pass
		}
		else
		{
			log.info("User can view Add new customer page- failed");

			Assert.assertTrue(false);//fail
		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		//addNewCustPg.enterEmail("cs129@gmail.com");
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Prachi");
		addNewCustPg.enterLastName("Gupta");
		addNewCustPg.enterGender("Female");
		addNewCustPg.enterDob("6/13/1988");
		addNewCustPg.enterCompanyName("CodeStudio");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");

		log.info("customer information entered");


	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustPg.clickOnSave();
		log.info("clicked on save button");

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view confirmation message - passed");

		}
		else
		{
			log.warn("User can view confirmation message - failed");

			Assert.assertTrue(false);//fail

		}

	}

	////////////Search Customer//////////////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("Email address entered");

	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
		log.info("Clicked on search button.");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if( SearchCustPg.searchCustomerByEmail(expectedEmail) ==true)
		{
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");

		}
		else {
			log.info("User should found Email in the Search table - passed");
			Assert.assertTrue(false);

		}


	}

	///////////////search customer by name////////////////////


	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		SearchCustPg.enterFirstName("Victoria");
	}
	
	@And("Select {string} Benificary contact")
	public void Select_Benificary_contact(String Ben) {
		SearchCustPg.enterBenificary(Ben);
	}
	
	@And("Logout Application")
	public void Logout_Application() {
		loginPg.clickOnLogOutButton();
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		SearchCustPg.enterLastName("Terces");
	}
	
	@Then("User can view Paid confirmation message")
	public void User_can_view_Requested_confirmation_message() {
		
		
		if(SearchCustPg.paidconfirmMsg().contains("Success"))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view Paid confirmation message - passed");

		}
		else
		{
			log.info("User can view Paid confirmation message - failed");
			Assert.assertTrue(false);//fail

		}
	}
	
	@Then("User can view Requested confirmation message")
	public void User_can_view_Paid_confirmation_message() {
		
		
		if(SearchCustPg.RequestedconfirmMsg().contains("Success"))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view Paid confirmation message - passed");

		}
		else
		{
			log.info("User can view Paid confirmation message - failed");
			Assert.assertTrue(false);//fail

		}
	}
	
	@And("Enter Amount {string} and ADDNote {string}")
	public void Enter_Amount_and_AddNote(String Amount,String AddNote) {
		SearchCustPg.EnterAmountandNote(Amount,AddNote);
	}
	
	@And("Submit Transaction")
	public void Submit_Transaction() {
		SearchCustPg.Submit_Transaction();
	}
	
	@And("Request Transaction")
	public void Request_Transaction() {
		SearchCustPg.Request_Transaction();
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";


		if( SearchCustPg.searchCustomerByName(expectedName) ==true)
		{
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);

	}

	@After
	public void teardown(Scenario sc)
	{
		System.out.println("Tear Down method executed..");
		if(sc.isFailed()==true)
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "D:\\CucumberFramework-master (1)\\CucumberFramework-master\\CucumberFramework\\Screenshot\\failedScreenshot.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}

	/*@After
	public void teardown2()
	{
		System.out.println("Tear Down method executed..");
		driver.quit();
	}*/

	/*@BeforeStep
	public void beforeStepMethodDemo()
	{
		System.out.println("This is before step....");
	}


	@AfterStep
	public void afterStepMethodDemo()
	{
		System.out.println("This is after step....");
	}*/


}
