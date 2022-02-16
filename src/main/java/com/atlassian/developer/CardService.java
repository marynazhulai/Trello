package com.atlassian.developer;

import com.atlassian.developer.dto.board.Color;
import io.restassured.response.ValidatableResponse;

public class CardService extends BaseService {

    public ValidatableResponse createCard(final String idList) {
        return getBaseRequestSpecification()
                .queryParams("idList", idList)
                .post("/1/cards/")
                .then();
    }

    public ValidatableResponse updateCardColor(final String id, Color color) {
        return getBaseRequestSpecification()
                .pathParam("id", id)
                .queryParams("color", color)
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

