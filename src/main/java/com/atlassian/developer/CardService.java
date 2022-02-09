package com.atlassian.developer;

import io.restassured.response.ValidatableResponse;

public class CardService extends BaseService {

    public ValidatableResponse createCard(final String idList) {
        return getBaseRequestSpecification()
                .queryParams("idList", idList)
                .post("/1/cards/")
                .then();
    }
}

