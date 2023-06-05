package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;

	public SearchCustomerPage(WebDriver rDriver)
	{
		ldriver=rDriver;

		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id="SearchEmail")
	WebElement emailAdd;

	@FindBy(id="search-customers")
	WebElement searchBtn;

	@FindBy(xpath="//table[@role='grid']")
	WebElement searchResult;

	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;

	/*@FindBy(xpath="//table[@id='customers-grid']//tbody/tr[1]/td")
	List<WebElement> tableColumns;*/

	@FindBy(id="SearchFirstName")
	WebElement firstName;
	
	@FindBy(id="user-list-search-input")
	WebElement EnterBenficiary;

	@FindBy(id="SearchLastName")
	WebElement lastName;
	
	@FindBy(id="amount")
	WebElement Amount;
	
	@FindBy(xpath="//h2[contains(text(),'Paid ')]")
	WebElement PaidConfirmationMsg;
	
	@FindBy(xpath="//h2[contains(text(),'Requested ')]")
	WebElement requestConfirmationMsg;
	
	@FindBy(xpath="//button//span[contains(text(),'Pay')]")
	WebElement Pay;
	
	@FindBy(xpath="//button//span[contains(text(),'Request')]")
	WebElement Request;
	
	
	@FindBy(id="transaction-create-description-input")
	WebElement AddNote;

	//action method to enter email address
	public void enterEmailAdd(String email)
	{
		emailAdd.sendKeys(email);
	}

	//action method to perform click action on search button
	public void clickOnSearchButton()
	{
		searchBtn.click();
		
	}


	public boolean searchCustomerByEmail(String email)
	{
		boolean found = false;

		//total no. of rows in a grid
		int ttlRows = tableRows.size();


		//total no. of columns
		//int ttlColumns = tableColumns.size();

		for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
		{
			System.out.println("Searching row:" +i );

			WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[2]"));
			String actualEmailAdd = webElementEmail.getText();
			System.out.println(actualEmailAdd);

			if(actualEmailAdd.equals(email))
			{
				found=true;
			}


		}

		return found;

	}
	///////////////////////search customer by name///////////////////////////////
	//action method to enter first name
	public void enterFirstName(String firstNameText)
	{
		firstName.sendKeys(firstNameText);
	}
	
	public void selectenterBenificary(String beni) {
		ldriver.findElement(By.xpath("//span[contains(text(),'" + beni + "')]")).click();
		
		}
	public void enterBenificary(String benfi)
	{	
		
		EnterBenficiary.sendKeys(benfi);
		selectenterBenificary(benfi);
		
	}

	//action method to enter last name
	public void enterLastName(String LastNameText)
	{
		lastName.sendKeys(LastNameText);
	}
	
	public void EnterAmountandNote(String amt,String note)
	{
		Amount.sendKeys(amt);
		AddNote.sendKeys(note);
	}
	
	public String paidconfirmMsg()
	{ 
		System.out.println("(PaidConfirmationMsg.getText():"+PaidConfirmationMsg.getText());
		if((PaidConfirmationMsg.getText()).contains("Paid ")){
			
			return "Success";
		}else {
		return "Failed";
		}
	}
	public String RequestedconfirmMsg()
	{ 
		
		if((requestConfirmationMsg.getText()).contains("Requested ")){
			
			return "Success";
		}else {
		return "Failed";
		}
	}
	
	public void Submit_Transaction()
	{
		Pay.click();
		
	}
	
	public void Request_Transaction()
	{
		Request.click();
		
	}
	
	
	public boolean searchCustomerByName(String name)
	{
		boolean found = false;

		//total no. of rows in a grid
		int ttlRows = tableRows.size();


		for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
		{
			WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[3]"));
			String actualName = webElementName.getText();

			if(actualName.equals(name))
			{
				found=true;
				break;
			}


		}

		return found;

	}
}
