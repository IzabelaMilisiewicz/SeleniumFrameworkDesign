package Tests.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Tests/Cucumber",
        glue="Tests.StepDefinitions",
        monochrome = true,
        tags = "@ErrorValidation",
        plugin={"html:target/cucumber.html"})
//helper attributes for example groups; in brackets: where is our cucumber file, where are step definitions,
// generate report in html; monochrome are reports
//inbuild cucumber cannot scan testng code like assertions and libraries but you can use AbstractTestNGCucumberTests - there is
//a lot of wrappers thank to which cucumber will understand testNG

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
