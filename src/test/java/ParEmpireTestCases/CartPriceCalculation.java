package ParEmpireTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CartPriceCalculation extends BaseTest{

	@Test
	public void cartCalc() throws InterruptedException {
		By beverageCategoryMenuLocator = By.xpath("//a[@href=\"/category/beverages\"]");
		WebElement beverageCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(beverageCategoryMenuLocator));
		beverageCategoryElement.click();
		
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Search here\")]")).click();	
		//Thread.sleep(2000);
		By plpFirstProductMiniCartBtnLocator = By.xpath("(//div[@class=\"addtocard\"])[1]");
		WebElement plpFirstBtnCartBtnList = wait.until(ExpectedConditions.elementToBeClickable(plpFirstProductMiniCartBtnLocator));
	    plpFirstBtnCartBtnList.click();
		
		By addToCartBtnLocator = By.cssSelector(".add-cart-btn");
		WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtnLocator));
		addToCartBtn.click();
		
	    Thread.sleep(2000);
		
		By plpSecondProductMiniCartBtnLocator = By.xpath("(//div[@class=\"addtocard\"])[2]");
		WebElement plpSecondBtnCartBtnList = wait.until(ExpectedConditions.elementToBeClickable(plpSecondProductMiniCartBtnLocator));
		plpSecondBtnCartBtnList.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtnLocator)).click();
		
		Thread.sleep(2000);
		By cartBtnLocatore = By.cssSelector("#km-bag");
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartBtnLocatore));
		
		cartBtn.click();	
		
		By cartItemsLocator = By.cssSelector(".grid.boxes");
		List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItemsLocator));
		
		By icreaseQtyBtnLocator = By.xpath("(//a[contains(@id,\"qty_puls\")])[1]");
		WebElement icreaseQtyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(icreaseQtyBtnLocator));
		icreaseQtyBtn.click();
		
		Thread.sleep(2000);
		//System.out.println(cartItems.size());
		
		
		double calculatedTotalPrice = 0.0;
		
		for(WebElement item: cartItems) {
			        int quantity = 1;
			        WebElement quantityElement = item.findElement(By.cssSelector(".text-center"));
			        String qtyText = (String) js.executeScript("return arguments[0].textContent;", quantityElement);
		            qtyText = qtyText.replaceAll("[^0-9]", "").trim();
			        quantity = Integer.parseInt(qtyText); // if empty, it will go to catch
			        System.out.println("Qty: " + quantity);

			        WebElement priceElement = item.findElement(By.cssSelector(".price-selling"));
			        String priceText = (String) js.executeScript("return arguments[0].textContent;", priceElement);
			        priceText = priceText.replaceAll("[^0-9.]", "").trim();
			        double price = Double.parseDouble(priceText); // if empty, it will go to catch
			        System.out.println("Price: " + price);

			        calculatedTotalPrice += price * quantity;
			        System.out.println("calculation: "+calculatedTotalPrice);			    
		}
	    
		WebElement totalPriceLocator = driver.findElement(By.xpath("(//span[contains(@id,\"m_sub_total\")])[2]"));
		double totalPrice = Double.parseDouble(totalPriceLocator.getText().replaceAll("[^0-9.]", ""));
		
		System.out.println(calculatedTotalPrice+"="+totalPrice);
	}
	
}
