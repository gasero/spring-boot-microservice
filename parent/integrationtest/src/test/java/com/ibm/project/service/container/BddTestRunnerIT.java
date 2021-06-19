package com.ibm.project.service.container;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}, snippets = SnippetType.CAMELCASE,
        glue = {"com.ibm.project.service.container.steps"})
public class BddTestRunnerIT {
}
