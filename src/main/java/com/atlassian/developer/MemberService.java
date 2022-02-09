package com.atlassian.developer;

import io.restassured.response.ValidatableResponse;

public class MemberService extends BaseService {

    private static final String CURRENT_USER_ID = "me";
    private static final String CURRENT_BOARD_ID = "62018f429e16615232ad0c0a";
    private static final String CURRENT_LIST_ID = "62018f429e16615232ad0c0b";

    public ValidatableResponse getMemberBoards() {
        return getMemberBoards(CURRENT_USER_ID);
    }

    public ValidatableResponse getMemberBoards(final String memberId) {
        return getBaseRequestSpecification()
                .pathParam("id", memberId)
                .get("/1/members/{id}/boards")
                .then();
    }

    public ValidatableResponse getMemberCards() {
        return getMemberCards(CURRENT_USER_ID);
    }

    public ValidatableResponse getMemberCards(final String memberId) {
        return getBaseRequestSpecification()
                .pathParam("id", memberId)
                .get("/1/members/{id}/boards/62018f429e16615232ad0c0a/lists/62018f429e16615232ad0c0b/cards")
                .then();
    }
}
