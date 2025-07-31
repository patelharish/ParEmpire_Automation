package ParEmpireTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

	@Test
	public void loginWithValidCredentials() {
	 loginApplication();
	 By successMsgLocator = By.cssSelector(".toasted.success");
	 WebElement successMsg = wait.until(ExpectedConditions.presenceOfElementLocated(successMsgLocator));
	 
	 String actualMsg = successMsg.getText().trim();
	 String expectedMsg = "You have logged in successfully.";
	 
	 Assert.assertEquals(actualMsg,expectedMsg);
	 
	 By myDashboardBtnLocator = By.xpath("(//a[@href=\"/account/dashboard\"])[1]");
	 WebElement myDashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(myDashboardBtnLocator));
	 myDashboardBtn.click();
	 
	 By profileInfoLocator = By.cssSelector(".user-profile-section");
	 WebElement profileInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(profileInfoLocator));
	 Assert.assertTrue(profileInfo.isDisplayed(),"Dashboard info should be visible on the profile page");
	 
	 By signOutBtnLocator = By.cssSelector(".sign-out-link");
	 WebElement signOutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signOutBtnLocator));
	 Assert.assertTrue(signOutBtn.isDisplayed(),"sign out Button should be visible on the profile page");
	 signOutBtn.click();
	 
	}
}
