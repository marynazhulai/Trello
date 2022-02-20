package com.atlassian.developer;
import com.atlassian.developer.dto.board.BoardDTO;
import com.atlassian.developer.dto.board.CardDTO;
import com.atlassian.developer.dto.board.ListService;
import com.atlassian.developer.dto.board.ListsDTO;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertEquals;

public class UpdateCardName {
    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();
        final BoardService boardService = new BoardService();
        final String name = Faker.instance().funnyName().name();
        final BoardDTO newBoard = boardService.createBoard(name)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNotNull(newBoard.getId());

        final List<ListsDTO> lists = new ListService().getMemberLists(newBoard.getId())
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

        final CardDTO updateCard = cardService.updateCardName(card.getId(), "First card")
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
        assertNotNull(updateCard.getId());
        assertEquals(card.getId(), updateCard.getId(), "ID not matched");
        assertEquals(updateCard.getCardName(), "First card", "Card name not match");
    }
}
