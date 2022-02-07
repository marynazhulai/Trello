package com.atlassian.developer;

import com.atlassian.developer.dto.board.BoardDTO;
import com.atlassian.developer.dto.board.MemberService;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testBoardCreation() {
        final BoardService boardService = new BoardService();
        final String name = Faker.instance().funnyName().name();
        final BoardDTO entity = boardService.createBoard(name)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNotNull(entity.getId());
        assertEquals(entity.getName(),name, "Name not match");
        final List<BoardDTO> boardList = new MemberService().getMemberBoards()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$", BoardDTO.class);
        assertTrue(boardList.stream().anyMatch(b -> b.getId().equals(entity.getId())));

        //To do afterMethod
        //delete created data

    }
}