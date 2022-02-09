package com.atlassian.developer;

import com.atlassian.developer.dto.board.CardDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testCardCreation() {
        final CardService cardService = new CardService();
        final String idList = "62018f429e16615232ad0c0b";

        final CardDTO entity = cardService.createCard(idList)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CardDTO.class);
        assertNotNull(entity.getIdList());
        assertEquals(entity.getIdList(), idList, "Card ID not match");
        final List<CardDTO> cardList = new MemberService().getMemberCards()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$", CardDTO.class);
        assertTrue(cardList.stream().anyMatch(b -> b.getIdList().equals(entity.getIdList())));

    }
}
