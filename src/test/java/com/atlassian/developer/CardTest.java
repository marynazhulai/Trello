package com.atlassian.developer;
import com.atlassian.developer.dto.board.CardDTO;
import com.atlassian.developer.dto.board.ListService;
import com.atlassian.developer.dto.board.ListsDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest extends BaseTest {

    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();
        //final BoardService boardService = new BoardService();
        //final String name = Faker.instance().funnyName().name();

        /*final BoardDTO createBoard = boardService.createBoard(name)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNotNull(createBoard.getId());*/

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
        assertNotNull(card.getIdList());
        assertEquals(card.getIdList(), listId, "Card ID not match");
    }
}
