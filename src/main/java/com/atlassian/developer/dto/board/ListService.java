package com.atlassian.developer.dto.board;

import com.atlassian.developer.BaseService;
import io.restassured.response.ValidatableResponse;

public class ListService extends BaseService {

    private static final String CURRENT_USER_ID = "me";
    private static final String CURRENT_LIST_ID = "62018f429e16615232ad0c0b";

    public ValidatableResponse getMemberCards() {
        return getMemberCards(CURRENT_USER_ID);
    }

    public ValidatableResponse getMemberCards(final String memberId) {
        return getBaseRequestSpecification()
                .pathParam("id", CURRENT_LIST_ID)
                .get("/1/lists/{id}/cards")
                .then();
    }
}
