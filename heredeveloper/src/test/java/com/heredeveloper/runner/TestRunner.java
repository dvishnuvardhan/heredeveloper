package com.heredeveloper.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",
        glue     = {"com.heredeveloper.stepdefinition"},
        monochrome = true,
        plugin   = { "html:target/cucumber-reports/cucumber-html-report",
                     "json:target/cucumber-reports/CucumberTestReport.json",
                     "pretty:target/cucumber-reports/cucumber-pretty.txt",
                     "usage:target/cucumber-reports/cucumber-usage.json",
                     "junit:target/cucumber-reports/cucumber-results.xml"
                    } )

public class TestRunner {

	
}