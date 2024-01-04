package com.tyss.genericutility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tyss.objectrepository.HomePage;
import com.tyss.objectrepository.LoginPage;
import com.tyss.objectrepository.WelcomePage;

public class BaseClass {
	public JavaUtility javaLib=new JavaUtility();
	public FileUtility fileLib=new FileUtility();
	public ExcelUtility excelLib=new ExcelUtility();
	
	public WebDriverWait wait;
	public static ExtentReports report;
	public static ExtentTest test;
	public static WebDriver driver;
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
	
	@BeforeSuite(alwaysRun = true)
	public void configReports() {
		String TIME = javaLib.getSystemTime().toString().replace(":", "-");
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_report/extentReport_"+TIME+".html");
		report=new ExtentReports();
		report.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browserName) throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		String URL = fileLib.getDataFromPropertyFile("url");
		driver.get(URL);
	}
	@BeforeMethod(alwaysRun = true)
	public void login(Method method) throws EncryptedDocumentException, IOException {
		test=report.createTest(method.getName());
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		String EMAIL = excelLib.getDataFromExcel("Login",1,0);
		String PASSWORD = excelLib.getDataFromExcel("Login", 1, 1);
		lp=new LoginPage(driver);
		lp.getEmailTextField().sendKeys(EMAIL);
		lp.getPadsswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();	
	}	
	
	@AfterMethod(alwaysRun = true)
	public void logout() {
		hp=new HomePage(driver);
		hp.getLogoutLink().click();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	@AfterSuite(alwaysRun = true)
	public void reportBackup() {
		report.flush();
	}
}
