package com.atlassian.developer.dto.board;

import com.atlassian.developer.BaseService;
import io.restassured.response.ValidatableResponse;

public class MemberService extends BaseService {

    private static final String CURRENT_USER_ID = "me";

    public ValidatableResponse getMemberBoards() {
        return getMemberBoards(CURRENT_USER_ID);
    }

    public ValidatableResponse getMemberBoards(final String memberId) {
        return getBaseRequestSpecification()
                .pathParam("id", memberId)
                .get("/1/members/{id}/boards")
                .then();
    }
}
