package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//font[contains(text(),'User: Naveen K')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
		
	}

	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}

	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).click(newContactLink).build().perform();
	}
	
}
