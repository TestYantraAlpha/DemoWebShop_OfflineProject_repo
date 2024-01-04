package com.tyss.objectrepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Log out")
	private WebElement logoutLink;

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	@FindBy(xpath = "//p[@class='content']")
	private WebElement productAddedMsg;
	
	public WebElement getProductAddedMsg() {
		return productAddedMsg;
	}

	@FindBy(xpath = "//input[@value='Add to cart']")
	private List<WebElement> addToCartButtons;

	public List<WebElement> getAddToCartButtons() {
		return addToCartButtons;
	}
	
	
}
