package com.atlassian.developer.business.object;

import com.atlassian.developer.CardService;
import com.atlassian.developer.dto.board.CardDTO;
import org.apache.http.HttpStatus;

public class CardBO {
    String listId;
    final CardService cardService = new CardService();

    public CardBO(String listId) {
        this.listId = listId;
    }

    public CardDTO createCard() {
        final CardDTO card = cardService.createCard(listId)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
        return card;
    }
}
