package FrameWorks.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="\\src\\test\\java\\FrameWorks\\Cucumber\\",glue="FrameWorks.stepDefinition",monochrome=true,plugin= {"html;traget/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests{

}
