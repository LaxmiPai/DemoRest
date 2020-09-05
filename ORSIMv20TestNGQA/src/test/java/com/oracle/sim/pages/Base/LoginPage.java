package com.oracle.sim.pages.Base;

import org.openqa.selenium.By;

import com.oracle.sim.pages.Base.SimBasePage;

public class LoginPage extends SimBasePage {
	
	public LoginPage() throws Exception {
		super();
		
		/*
		 * addObject("Username", By.xpath("//input[@placeholder='User Name']"));
		 * addObject("Password", By.xpath("//input[@placeholder='Password']"));
		 * addObject("SignIn", By.xpath("//span[text()='Sign In']"));
		 */		
		
	//	addObject("Username", By.xpath("//input[contains(@id, 'username')]"));
		addObject("Username",By.xpath("//label[@class='oj-component-initnode'and text()='User Name']//following::input[1]"));
		addObject("Password", By.xpath("//input[contains(@id, 'password')]"));		
		addObject("SignIn", By.xpath("//span[text()='Sign In']"));
		

		
		
		

	

	}

}
