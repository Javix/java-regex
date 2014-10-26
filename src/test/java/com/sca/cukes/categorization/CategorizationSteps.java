package com.sca.cukes.categorization;

import com.sca.xml.Categorization;
import com.sca.xml.DocumentBean;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Serguei CAMBOUR on 25/10/14.
 */
public class CategorizationSteps {

    private String categorization;
    private String[] groups;
    private Pattern pattern;
    private Matcher matcher;
    private DocumentBean documentBean;
    private String group;


    @Given("^a categorization with following groups:$")
    public void a_categorization_with_following_groups(String categorization) {
        this.categorization = categorization;
    }

    @When("^I parse the categorization$")
    public void i_parse_the_categorization() {
       groups = categorization.split(",");
    }

    @Then("^it should contain the following groups:$")
    public void it_should_contain_the_following_groups(List<String> entries)  {
       assertThat(entries.size(), is(3));
       for(String group: groups) {
           org.junit.Assert.assertThat(entries, hasItem(group));
       }
    }

    @Given("^the following \"(.*?)\"$")
    public void the_following_group(String group)  {
       this.group = group;
    }

    @When("^it matches the \"(.*?)\"$")
    public void it_match_the(String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(group);
    }

    @Then("^the \"(.*?)\" should have the \"(.*?)\" set up$")
    public void the_should_have_the_set_up(String attribute, String value) {
        documentBean = new DocumentBean();
        if (attribute.equals(Categorization.META)) {
            documentBean.setMeta(matcher.group(2));
            assertEquals(documentBean.getMeta(), value);
        }
    }

}
