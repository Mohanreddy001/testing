package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"D:\\batch239\\w2smstesting\\src\\test\\resources\\mypack\\feature1.feature","D:\\batch239\\w2smstesting\\src\\test\\resources\\mypack\\feature2.feature"},plugin={"pretty","html:target"},monochrome=true)

public class Realrunner
{
	

}
