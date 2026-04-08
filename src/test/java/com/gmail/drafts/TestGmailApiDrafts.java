package com.gmail.drafts;

import com.gmail.TestBase;
import com.gmail.api.drafts.DraftsApi;
import com.gmail.pojo.drafts.DraftRoot_PJ;
import com.gmail.pojo.drafts.Headers_PJ;
import com.gmail.pojo.drafts.Message_PJ;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

import static com.gmail.utils.Constants.*;
import static com.gmail.utils.PropertyLoader.getMyProperty;
import static com.gmail.utils.SpecBuilder.getRequestSpecification;
import static com.gmail.utils.SpecBuilder.postRequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Epic("EPIC-1234")
@Feature("Feature-1234")
@Story("STORY-1234")
public class TestGmailApiDrafts extends TestBase {

    String draftId;
    String draftUpdateId;
    DraftsApi draftsApi;

    @BeforeMethod
    void setup(){
        draftsApi = new DraftsApi();
    }

    @Test(priority = 0)
    @Description("Should be able to list all Drafts")
    void testListAllDraftsEmails(){
        Response responsePayload = draftsApi.listAllDrafts(getRequestSpecification(), DRAFTS_RPATH);
        DraftRoot_PJ responsePojo = responsePayload.as(DraftRoot_PJ.class);
        draftId=responsePojo.getDrafts().get(new Random().nextInt(30) + 1).getId();

        System.out.println("Thread ID : "+Thread.currentThread().getId()+" draftId :"+draftId);
        assertThat(responsePayload.statusCode(), equalTo(200));
        assertThat(responsePojo.getDrafts(), not(empty()));
    }

    @Test(priority = 1)
    @Description("Should be able to get a Draft")
    void testGetADraftEmail() throws InterruptedException {
        // This draft id has been hardcoded since it returns complex response
        draftId="r7928898994629193882";
                Response response = draftsApi.getADraft(getRequestSpecification(), DRAFTS_RPATH, draftId);
        assertThat(response.statusCode(), equalTo(200));
        System.out.println("Thread ID : "+Thread.currentThread().getId()+" draftId :"+draftId);
        DraftRoot_PJ response_pojo = response.as(DraftRoot_PJ.class);

        assertThat(response_pojo.getId(), equalTo(draftId));
        assertThat(response_pojo.getMessage().getPayload().getHeaders().stream()
            .filter(header -> header.getName().equalsIgnoreCase("From"))
            .map(Headers_PJ::getValue).findFirst().orElseThrow()
        , containsString(getMyProperty(USER_ID)));
    }

    @Test(priority = 2)
    @Description("Should be able to create a Draft")
    void testCreateADraftEmail() throws InterruptedException {
        draftUpdateId = String.valueOf(new Random().nextInt());
        System.out.println("Thread ID : "+Thread.currentThread().getId()+" draftUpdateId :"+draftUpdateId);
        DraftRoot_PJ requestPayload = new DraftRoot_PJ();
        requestPayload.setId(draftUpdateId);
        requestPayload.setMessage(Message_PJ.builder().raw(base64UrlEncoder(CREATE_DRAFT_EMAIL_TEXT)).build());
        Response response = draftsApi.createADraft(postRequestSpecification(), DRAFTS_RPATH, requestPayload);
        DraftRoot_PJ response_pojo = response.as(DraftRoot_PJ.class);
        draftUpdateId = response_pojo.getId();

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response_pojo.getId(), notNullValue());
        assertThat(response_pojo.getMessage().getLabelIds(), not(empty()));
    }


    @Test(priority = 3)
    @Description("Should be able to update a Draft")
    void testUpdateADraftEmail() throws InterruptedException {
        System.out.println("Thread ID : "+Thread.currentThread().getId()+" draftUpdateId :"+draftUpdateId);
        DraftRoot_PJ requestPayload = new DraftRoot_PJ();
        requestPayload.setId(draftUpdateId);
        requestPayload.setMessage(Message_PJ.builder().raw(base64UrlEncoder(UPDATE_DRAFT_EMAIL_TEXT)).build());
        Response response = draftsApi.updateADraft(postRequestSpecification(), DRAFTS_RPATH, requestPayload, draftUpdateId);

        assertThat(response.statusCode(), equalTo(200));
        DraftRoot_PJ response_pojo = response.as(DraftRoot_PJ.class);
        assertThat(response_pojo.getId(), equalTo(draftUpdateId));
    }

    @Test(priority = 4)
    @Description("Should be able to delete a Draft")
    void testDeleteADraftEmail() throws InterruptedException {
        System.out.println("Thread ID : "+Thread.currentThread().getId()+" draftUpdateId :"+draftUpdateId);
        Response response=draftsApi.deleteADraft(getRequestSpecification(), DRAFTS_RPATH, draftUpdateId);
        assertThat(response.statusCode(), equalTo(204));
    }

    String base64UrlEncoder(String originalText){
        return Base64.getUrlEncoder().encodeToString(originalText.getBytes(StandardCharsets.UTF_8));
    }
}