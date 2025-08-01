package ParEmpireTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {
	
	@Test(description = "verify plp wishlist functionality")
	public void verifyPlpWishlist() throws InterruptedException{
		loginApplication();
		By beverageCategoryMenuLocator = By.xpath("//a[contains(@href, 'category/beverages')]");
		WebElement beverageCategoryElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(beverageCategoryMenuLocator));
		beverageCategoryElement.click();
	
		By wishlistBtnLocator = By.cssSelector("#km-wishlist");
		WebElement wishlistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistBtnLocator));
		wishlistBtn.click();

		Thread.sleep(2000);

		By wlremoveBtnLocator = By.cssSelector(".fas.km-icons");
		List<WebElement> wlremoveBtns = driver.findElements(wlremoveBtnLocator);

		if (!wlremoveBtns.isEmpty()) {
			for (WebElement removeBtn : wlremoveBtns) {
				wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".modal-footer .btn-danger")))
						.click();
			}
		} else {
			System.out.println("no products are available in wishlist");
		}

		driver.navigate().back();

		By plpwishlistLocator = By.cssSelector(".wislist-box");
		List<WebElement> plpWishlistList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(plpwishlistLocator, 5));

		
		wait.until(ExpectedConditions.elementToBeClickable(plpWishlistList.get(0))).click();

	
		wait.until(ExpectedConditions.elementToBeClickable(plpWishlistList.get(1))).click();
		
		By wlPopUpmsgLocator = By.cssSelector(".toastify-right");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(wlPopUpmsgLocator));
		
		By cartNotificationLocator = By.cssSelector("#wishlist-count"); 
		WebElement cartNotification = wait
				.until(ExpectedConditions.visibilityOfElementLocated(cartNotificationLocator));

		Assert.assertEquals(cartNotification.getText(), "2", "notification matches failed");

	}
}
