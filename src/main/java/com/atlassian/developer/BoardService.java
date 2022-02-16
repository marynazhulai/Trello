package com.atlassian.developer;

import io.restassured.response.ValidatableResponse;

public class BoardService extends BaseService {

    public ValidatableResponse createBoard(final String name) {
        return getBaseRequestSpecification()
                .queryParams("name", name)
                .post("/1/boards/")
                .then();
    }

    public ValidatableResponse deleteBoard(final String id) {
        return getBaseRequestSpecification()
                .pathParam("id",id)
                .delete("/1/boards/{id}")
                .then();
    }
}
