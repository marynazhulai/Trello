package com.atlassian.developer;
import com.atlassian.developer.dto.board.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertEquals;

public class UpdateCardColor extends BaseTest{
    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();

        final List<ListsDTO> lists = new ListService().getMemberLists(board.getId())
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$", ListsDTO.class);

        ListsDTO firstList = lists.get(0);
        String listId = firstList.getIdList(lists);

        final CardDTO card = cardService.createCard(listId)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
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

