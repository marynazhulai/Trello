package com.atlassian.developer;

import com.atlassian.developer.business.object.CardBO;
import com.atlassian.developer.business.object.ListsBO;
import com.atlassian.developer.dto.board.CardCoverDTO;
import com.atlassian.developer.dto.board.CardDTO;
import com.atlassian.developer.dto.board.Color;
import com.atlassian.developer.dto.board.ListsDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertEquals;

public class UpdateCardColor extends BaseTest {
    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();

        final ListsBO listsBO = new ListsBO(board);
        ListsDTO firstList = listsBO.getLists().get(0);
        String listId = firstList.getIdList(listsBO.getLists());

        final CardBO cardBO = new CardBO(listId);
        final CardDTO card = cardBO.createCard();
        assertNotNull(card.getId());

        final CardCoverDTO cardCover = new CardCoverDTO();
        cardCover.setColor(Color.BLUE);

        final CardDTO updateColor = cardService.updateCardCover(card.getId(), cardCover)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
        assertNotNull(card.getId());
        assertEquals(updateColor.getCover().getColor(), Color.BLUE, "Card color not match");
    }
}

