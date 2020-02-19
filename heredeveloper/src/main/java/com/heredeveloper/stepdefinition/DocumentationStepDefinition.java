package com.heredeveloper.stepdefinition;

import static com.heredeveloper.stepdefinition.Hooks.driver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.heredeveloper.generic.BaseLibrary;
import com.heredeveloper.pages.DocumentionObjects;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DocumentationStepDefinition extends BaseLibrary {
	SoftAssert softAssert = new SoftAssert();
	DocumentionObjects docObjs;

	@When("^user is already on developer documentation page (.+)$")
	public void user_is_already_on_developer_documentation_page(String url) throws Throwable {
		driver.get(url);
	}

	@Then("^validate all internal links and AngularJS initialized$")
	public void validate_all_internal_links_and_AngularJS_initialized() throws Throwable {
		String url = null;
		docObjs = new DocumentionObjects(driver);

		waitForElement(docObjs.cardDetail);
		List<WebElement> listOfcardFooterSections = docObjs.cardFooterSections;
		for (int i = 1; i <= listOfcardFooterSections.size(); i++) {
			List<WebElement> listOfCardFooterLinks = docObjs.cardFooterLinks(i);
			for (int j = 1; j <= listOfCardFooterLinks.size(); j++) {
				boolean isDropDownVariant = docObjs.isdropDownVariant(i, j);
				if (isDropDownVariant == true) {
					docObjs.dropDownVariant(i, j).click();
					List<WebElement> dropDownVariantSubLinks = docObjs.dropDownVariantSubLinks(i, j);
					for (int k = 1; k <= dropDownVariantSubLinks.size(); k++) {
						docObjs.dropDownVariant(i, j).click();
						url = docObjs.dropDownSubLink(i, j, k).getAttribute("href");
						System.out.println("URL :: " + url);
						docObjs.dropDownSubLink(i, j, k).click();
						int statusCode = getURLRequestResponseCode(url);
						System.out.println("Status Code:: " + statusCode);
						softAssert.assertEquals(statusCode, 200,
								"Fail :: Error code mimatch, Actual code - " + statusCode);
						boolean isAngulaJsLoaded = angularLoads();
						System.out.println("Is AngularJS loaded? ::" + isAngulaJsLoaded);
						softAssert.assertEquals(isAngulaJsLoaded, true, "Fail :: AngularJS is not loaded");
						driver.navigate().back();
						waitForElement(docObjs.cardDetail);
					}

				} else {
					url = docObjs.cardFooterLink(i, j).getAttribute("href");
					System.out.println("URL :: " + url);
					if (url.contains("https://developer.here.com/documentation/")) {
						docObjs.cardFooterLink(i, j).click();
						int statusCode = getURLRequestResponseCode(url);
						System.out.println("Status Code:: " + statusCode);
						softAssert.assertEquals(statusCode, 200,
								"Fail :: Error code mimatch, Actual code - " + statusCode);
						boolean isAngulaJsLoaded = angularLoads();
						System.out.println("Is AngularJS loaded? ::" + isAngulaJsLoaded);
						softAssert.assertEquals(isAngulaJsLoaded, true, "Fail :: AngularJS is not loaded");
						driver.navigate().back();
						waitForElement(docObjs.cardDetail);
					} else {
						System.out.println("This url not relative to documenatation ");
					}

				}

			}
		}

	}

}