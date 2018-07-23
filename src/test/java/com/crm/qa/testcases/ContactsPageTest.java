package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	String sheetName="contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest(){
		Assert.assertTrue(contactsPage.verifyContacsLabel());
	}
	
	@Test(priority=2)
	public void selectContactsByNameTest(){
		contactsPage.selectContactsByname("abc abc");
		contactsPage.selectContactsByname("Atul Jose");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3,dataProvider="getCRMTestData")
	public void validateCreateNewConatct(String title,String firstname,String lastname,String company){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title,firstname,lastname,company);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
