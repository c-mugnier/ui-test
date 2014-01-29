package org.exoplatform.selenium.platform.user;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.PlatformBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Platform_SignUp_User extends PlatformBase{

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		//driver.get(baseUrl);
	}

	@AfterMethod
	public void afterMethods() {
		driver.quit();
	}
	
	@Test
	public void test00_AccountSetup(){
		info("Account setup");
	}
}
