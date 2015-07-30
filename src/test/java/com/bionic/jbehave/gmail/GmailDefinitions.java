package com.bionic.jbehave.gmail;

import com.bionic.google.GmailAuthorization;
import com.bionic.steps.GmailSteps;
import com.bionic.utils.PropertyLoader;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * Created by viktozhu on 7/23/15.
 */
public class GmailDefinitions {
    @Steps
    GmailSteps steps;

    @Given("authorized connection to gmail")
    public void authorizedConnection() throws IOException {
        PropertyLoader.loadProperties();
        GmailAuthorization gmailAuthorization = null;
        try {
            gmailAuthorization = new GmailAuthorization("bdd-project", "src/main/resources/secrets/client_secret_drive.json");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        Gmail service = gmailAuthorization.getGmailService();

        String user = "me";
        ListLabelsResponse listResponse =
                service.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.size() == 0) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
    }

    @When("I get list of emails")
    public void getEmailList(){

    }

    @Then("no new emails received")
    public void shouldNotBeNewEmails(){

    }
}