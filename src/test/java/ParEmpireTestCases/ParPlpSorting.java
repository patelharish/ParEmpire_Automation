package ParEmpireTestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class ParPlpSorting extends BaseTest{

	@Test()
	public void highToLowSorting() throws InterruptedException {
		By highToLowOptionLocator = By.cssSelector(".price-high-low");
		WebElement highToLowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(highToLowOptionLocator));
		//logger.info("Selecting High to Low Option...");
		highToLowElement.click();

		//logger.info("Scroll down to bottom...");
		for (int i = 0; i < 10; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(3000);

			try {
				WebElement endElement = driver.findElement(By.cssSelector(".result_end"));
				if (endElement.isDisplayed()) {
					System.out.println("End the results");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		By plpPricesLocator = By.cssSelector(".price");
		List<WebElement> priceListElement = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(plpPricesLocator, 40));

		List<Double> actualPrices = new ArrayList<>();

		for (WebElement list : priceListElement) {
			String priceText = list.getText().replaceAll("[^0-9.]", "");
			if (!priceText.isEmpty()) {
				actualPrices.add(Double.parseDouble(priceText));
			}
		}

		// copy of list
		List<Double> sortedPrice = new ArrayList<Double>();

		for (Double actual : actualPrices) {
			sortedPrice.add(actual);
		}
		// Collections.sort(sortedPrice); //asc
		sortedPrice.sort(Collections.reverseOrder()); // desc order

		if (actualPrices.equals(sortedPrice)) {
			System.out.println("✅ Prices are correctly sorted from high to low.");
		} else {
			System.out.println("❌ Prices are NOT sorted correctly.");
			System.out.println("Actual: " + actualPrices);
			System.out.println("Expected: " + sortedPrice);
		}

		Assert.assertEquals(actualPrices, sortedPrice);
		System.out.println("prices are sorted");
	}

	@Test
	public void LowToHighSorting() throws InterruptedException {
		By lowToHighOptionLocator = By.cssSelector(".price-low-high");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lowToHighOptionLocator)).click();

		for (int i = 0; i < 10; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(3000);

			try {
				WebElement endElement = driver.findElement(By.cssSelector(".result_end"));
				if (endElement.isDisplayed()) {
					System.out.println("End the results");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		By plpPricesLocator = By.cssSelector(".price");
		List<WebElement> priceListElement = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(plpPricesLocator, 40));

		List<Double> actualPrices = new ArrayList<>();

		for (WebElement list : priceListElement) {
			String priceText = list.getText().replaceAll("[^0-9.]", "");
			if (!priceText.isEmpty()) {
				actualPrices.add(Double.parseDouble(priceText));
			}
		}

		// copy of list
		List<Double> sortedPrice = new ArrayList<Double>();

		for (Double actual : actualPrices) {
			sortedPrice.add(actual);
		}
		Collections.sort(sortedPrice); // asc
		// sortedPrice.sort(Collections.reverseOrder()); //desc order

		if (actualPrices.equals(sortedPrice)) {
			System.out.println("✅ Prices are correctly sorted from Low to High.");
		} else {
			System.out.println("❌ Prices are NOT sorted Low to High correctly.");
			System.out.println("Actual: " + actualPrices);
			System.out.println("Expected: " + sortedPrice);
		}

		Assert.assertEquals(actualPrices, sortedPrice);
		System.out.println("prices are sorted");
	}

}
