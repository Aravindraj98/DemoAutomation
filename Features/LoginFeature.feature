Feature: Login 

@Sanity
Scenario: New Bank Account Creation
	Given User Launch Chrome browser 
	When User opens URL "http://localhost:3000/signin"
	And User verifies URL page-loaded "Sign in" success
	And User enters Email as "Tavares_Barrows" and Password as "s3cret"
	And Click on Login
	Then User can view Dashboad
	And User navigates to Bank Account Creation Window
	And User enters Bank Name as "ICCIC"
	And User enters Routing Number as "457812458"
	And User enters Account Number as "12457896581"
	And Click on Save
	When User click on Log out link 
	
#	Then Page Title should be "Your store. Login" 
#	And close browser
