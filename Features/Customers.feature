Feature: Customers

Background: Steps common for all scenrios
Given User Launch Chrome browser 
	When User opens URL "http://localhost:3000/signin"
	And User verifies URL page-loaded "Sign in" success
	And User enters Email as "Tavares_Barrows" and Password as "s3cret"
	And Click on Login
	Then User can view Dashboad
		
	@Sanity
	Scenario: Make New Fund Transaction
	When User click on New Transaction 
	And Select "Edgar Johns" Benificary contact
	And Enter Amount "30" and ADDNote "Transfer"
	And Submit Transaction
	Then User can view Paid confirmation message
	And Logout Application
	And close browser
	
	@Sanity
	Scenario: Make New Fund Transaction Request
	When User click on New Transaction 
	And Select "Kaylin Homenick" Benificary contact
	And Enter Amount "100" and ADDNote "Transfer"
	And Request Transaction
	Then User can view Requested confirmation message
	And Logout Application
	And close browser
	
	