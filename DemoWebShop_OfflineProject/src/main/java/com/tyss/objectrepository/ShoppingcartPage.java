package com.tyss.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingcartPage {
	public ShoppingcartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//a[@class='product-name']")
	private WebElement productName;

	public WebElement getProductName() {
		return productName;
	}
}
