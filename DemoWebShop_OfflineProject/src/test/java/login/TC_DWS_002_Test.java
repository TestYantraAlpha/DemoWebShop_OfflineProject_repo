package login;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tyss.genericutility.BaseClass;
import com.tyss.genericutility.ListenerUtility;

@Listeners(ListenerUtility.class)
public class TC_DWS_002_Test extends BaseClass {
	@Test(groups = "system")
	public void loginTest() throws EncryptedDocumentException, IOException {
		String EXPECTED_TITLE = excelLib.getDataFromExcel("Login", 1, 2);
		Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE,"User failed to login");
		test.log(Status.PASS, "User successfully logged in");
	}
}
