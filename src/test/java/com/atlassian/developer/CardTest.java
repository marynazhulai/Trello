package com.atlassian.developer;

import com.atlassian.developer.business.object.ListsBO;
import com.atlassian.developer.dto.board.CardDTO;
import com.atlassian.developer.dto.board.ListsDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest extends BaseTest {

    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();

        final ListsBO listsBO = new ListsBO(board);
        ListsDTO firstList = listsBO.getLists().get(0);
        String listId = firstList.getIdList(listsBO.getLists());

        final CardDTO card = cardService.createCard(listId)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
        assertNotNull(card.getIdList());
        assertEquals(card.getIdList(), listId, "Card ID not match");
    }
}
