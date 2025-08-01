package ParEmpireTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPriceCalculation extends BaseTest{

	@Test(description = "verify cart price calculation")
	public void cartCalc() throws InterruptedException {
		By beverageCategoryMenuLocator = By.xpath("//a[contains(@href, 'category/beverages')]");
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
	        double price = 0.0;

	        // === Get Quantity ===
	        try {
	            // Try to get from input if available, else fallback to text
	            WebElement qtyElement = item.findElement(By.cssSelector("input[type='text'], input.qty-input, .text-center"));
	            String qtyText = qtyElement.getAttribute("value");

	            if (qtyText == null || qtyText.trim().isEmpty()) {
	                qtyText = qtyElement.getText(); // fallback
	            }

	            qtyText = qtyText.replaceAll("[^0-9]", "").trim();

	            if (!qtyText.isEmpty()) {
	                quantity = Integer.parseInt(qtyText);
	            }

	           // System.out.println("Quantity: " + quantity);
	        } catch (Exception e) {
	            System.out.println("Failed to get quantity: " + e.getMessage());
	        }

	        // === Get Price ===
	        try {
	            WebElement priceElement = item.findElement(By.cssSelector(".price-selling")); // update selector if needed
	            String priceText = priceElement.getText().replaceAll("[^0-9.]", "").trim();

	            if (!priceText.isEmpty()) {
	                price = Double.parseDouble(priceText);
	            }

	           // System.out.println("Price: " + price);
	        } catch (Exception e) {
	            System.out.println("Failed to get price: " + e.getMessage());
	            continue;
	        }

	        double itemTotal = price * quantity;
	        calculatedTotalPrice += itemTotal;

	       // System.out.println("Item Total: " + price + " × " + quantity + " = " + itemTotal);
	    }

	    // === Get Displayed Cart Total ===
	    try {
	        WebElement totalPriceElement = driver.findElement(By.xpath("(//span[contains(@id,'m_sub_total')])[2]"));
	        String totalText = totalPriceElement.getText().replaceAll("[^0-9.]", "").trim();
	        double displayedTotal = Double.parseDouble(totalText);

	        System.out.println("\nCalculated Total: " + calculatedTotalPrice);
	        System.out.println("Displayed Total: " + displayedTotal);

	        // === Final Comparison using TestNG Assertion ===
            Assert.assertEquals(calculatedTotalPrice, displayedTotal, "❌ Cart total does not match!");

            System.out.println("Cart total matches successfully.");
            
	    } catch (Exception e) {
	        System.out.println("Failed to get displayed total price: " + e.getMessage());
	    }		    
}
	
}
