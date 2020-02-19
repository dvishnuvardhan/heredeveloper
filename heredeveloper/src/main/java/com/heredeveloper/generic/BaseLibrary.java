package com.heredeveloper.generic;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.heredeveloper.stepdefinition.Hooks.driver;

public class BaseLibrary {

	public static void waitForElement(WebElement element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static boolean angularLoads() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean isAngularJSLoaded = Boolean.valueOf((Boolean) js.executeScript(
				"return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)"));
		return isAngularJSLoaded;
	}

	public int getURLRequestResponseCode(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(new HttpGet(url));
		int statusCode = response.getStatusLine().getStatusCode();
		return statusCode;

	}

}
