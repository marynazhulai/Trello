package com.atlassian.developer;

import com.atlassian.developer.dto.board.CardCoverDTO;
import io.restassured.response.ValidatableResponse;

public class CardService extends BaseService {

    public ValidatableResponse createCard(final String idList) {
        return getBaseRequestSpecification()
                .queryParams("idList", idList)
                .post("/1/cards/")
                .then();
    }

    public ValidatableResponse updateCardCover(final String id, CardCoverDTO body) {
        return getBaseRequestSpecification()
                .pathParam("id", id)
                .body(body)
                .put("/1/cards/{id}")
                .then();
    }

    public ValidatableResponse updateCardName(final String id, final String name) {
        return getBaseRequestSpecification()
                .pathParam("id", id)
                .queryParams("name", name)
                .put("/1/cards/{id}")
                .then();
    }
}

