package com.atlassian.developer;
import com.atlassian.developer.dto.board.BoardDTO;
import com.github.javafaker.Faker;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertNull;

public class DeleteBoardTest extends BaseTest {
    static BoardDTO board;

    @Test
    public void deleteBoard() {
        final BoardService boardService = new BoardService();

        final String name = Faker.instance().funnyName().name();
        board = boardService.createBoard(name)
                .assertThat()
                .statusCode(org.apache.http.HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNotNull(board.getId());

        final BoardDTO deleteBoard = boardService.deleteBoard(board.getId())
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNull(deleteBoard.getId());
    }
}

