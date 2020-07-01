package me.philcali.io.file;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {
        "pretty",
        "json:cucumber/cucumber.json"
})
public class RunCucumberTest {
}
