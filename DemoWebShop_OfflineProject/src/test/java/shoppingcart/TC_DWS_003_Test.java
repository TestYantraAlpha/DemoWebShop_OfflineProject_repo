package shoppingcart;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tyss.genericutility.BaseClass;
import com.tyss.genericutility.ListenerUtility;
import com.tyss.objectrepository.HomePage;
import com.tyss.objectrepository.ShoppingcartPage;

@Listeners(ListenerUtility.class)
public class TC_DWS_003_Test extends BaseClass{
	@Test(groups = "system")
	public void addToCart() throws EncryptedDocumentException, IOException {
		hp=new HomePage(driver);
		hp.getAddToCartButtons().get(1).click();
		boolean productStatus = hp.getProductAddedMsg().isDisplayed();
		Assert.assertEquals(productStatus, true,"Product failed to add");
		test.log(Status.PASS, "Product added to cart message is displayed");
		wait.until(ExpectedConditions.invisibilityOf(hp.getProductAddedMsg()));
		wp.getShoppingCartLink().click();
		String EXPECTED_TITLE = excelLib.getDataFromExcel("ShoppingCart", 1	, 0);
		Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE,"Shopping cart page is not displayed");
		test.log(Status.PASS, "Shopping cart page is displayed");
		ShoppingcartPage sp=new ShoppingcartPage(driver);
		boolean ProductNameStatus = sp.getProductName().isDisplayed();
		Assert.assertEquals(ProductNameStatus, true,"Product failed to add to cart");
		test.log(Status.PASS, "Product added to cart successfully");
	}
}
