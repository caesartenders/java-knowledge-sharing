package nl.code4all;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "nl.code4all.defs", strict = true, features = "classpath:features/", tags = {"@Testing"})
public class RunScripts {

    /* Edit cucumber.properties */
}