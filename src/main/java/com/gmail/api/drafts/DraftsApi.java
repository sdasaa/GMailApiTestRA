package com.gmail.api.drafts;

import com.gmail.api.BaseApi;
import com.gmail.pojo.drafts.DraftRoot_PJ;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DraftsApi extends BaseApi {

    @Step
    public Response listAllDrafts(RequestSpecification requestSpecification, String draftsRPath){
        System.out.println("in DraftsApi.listAllDrafts");
        return listAllApi(requestSpecification, draftsRPath);
    }

    @Step
    public Response getADraft(RequestSpecification requestSpecification, String draftsRPath, String id){
        return getApi(requestSpecification, draftsRPath, id);
    }

    @Step
    public Response deleteADraft(RequestSpecification requestSpecification, String draftsRPath, String id){
        return deleteApi(requestSpecification, draftsRPath, id);
    }

    @Step
    public Response createADraft(RequestSpecification requestSpecification, String draftsRPath, DraftRoot_PJ requestPayload){
        return postApi(requestSpecification, draftsRPath, requestPayload);
    }

    @Step
    public Response updateADraft(RequestSpecification requestSpecification, String draftsRPath, DraftRoot_PJ requestPayload, String id){
        return putApi(requestSpecification, draftsRPath, requestPayload, id);
    }


}