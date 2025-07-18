package ParEmpireTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CartPriceCalculation extends BaseTest{

	@Test
	public void cartCalc() throws InterruptedException {
		By beverageCategoryMenuLocator = By.xpath("//a[@href=\"/category/beverages\"]");
		WebElement beverageCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(beverageCategoryMenuLocator));
		beverageCategoryElement.click();
		
		js.executeScript("window.scrollTo(0,0);");
		
		Thread.sleep(2000);
		By plpMiniCartBtnLocator = By.cssSelector(".addtocard");
		List<WebElement> plpCartBtnList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(plpMiniCartBtnLocator));
		plpCartBtnList.get(0).click();
		
		By addToCartBtnLocator = By.cssSelector(".add-cart-btn");
		WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtnLocator));
		addToCartBtn.click();
		
		Thread.sleep(3000);
		
		plpCartBtnList.get(1).click();
		Thread.sleep(2000);
		driver.findElement(addToCartBtnLocator).click();
		
		By cartBtnLocatore = By.cssSelector(".mini_cart_header");
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartBtnLocatore));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(cartBtn).click().perform();
			
		By icreaseQtyBtnLocator = By.xpath("(//a[contains(@id,\"qty_puls\")])[1]");
		WebElement icreaseQtyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(icreaseQtyBtnLocator));
		icreaseQtyBtn.click();
		
		By quantityInputLocator = By.cssSelector(".text-center");
		List<WebElement> quantityInput = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quantityInputLocator));
		System.out.println(quantityInput.get(0).getText());
		
		By cartItemsLocator = By.cssSelector(".grid.boxes");
		List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItemsLocator));
		
		double calculatedTotalPrice = 0.0;
		
		for(WebElement item: cartItems) {
		  WebElement quantityElement = item.findElement(By.cssSelector(".text-center"));
		  int quantity = Integer.parseInt(quantityElement.getText());
		  
		  WebElement priceElement = item.findElement(By.cssSelector(".price-selling"));
		  double price = Double.parseDouble(priceElement.getText().replaceAll("[^0-9.]", ""));
		  
		  calculatedTotalPrice += price*quantity;
		}
	    
		WebElement totalPriceLocator = driver.findElement(By.xpath("(//span[contains(@id,\"m_sub_total\")])[2]"));
		double totalPrice = Double.parseDouble(totalPriceLocator.getText().replaceAll("[^0-9.]", ""));
		
		System.out.println(calculatedTotalPrice+"="+totalPrice);
	}
	
}
