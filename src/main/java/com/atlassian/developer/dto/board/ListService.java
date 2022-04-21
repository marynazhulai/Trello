package com.atlassian.developer.dto.board;

import com.atlassian.developer.BaseService;
import io.restassured.response.ValidatableResponse;

public class ListService extends BaseService {

    private static final String CURRENT_USER_ID = "me";
    private static final String CURRENT_LIST_ID = "62018f429e16615232ad0c0b";
    private static final String CURRENT_BOARD_ID = "620952c7dc2b8a03a6384e53";

    public ValidatableResponse getMemberLists() {
        return getMemberLists(CURRENT_USER_ID);
    }

    public ValidatableResponse getMemberLists(final String boardId) {
        return getBaseRequestSpecification()
                .pathParam("id", boardId)
                .get("/1/boards/{id}/lists")
                .then();
    }

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
