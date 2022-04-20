package com.atlassian.developer;
import com.atlassian.developer.dto.board.BoardDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNull;

public class DeleteBoardTest extends BaseTest {

    @Test
    public void deleteBoard() {
        final BoardService boardService = new BoardService();

        final BoardDTO deleteBoard = boardService.deleteBoard(board.getId())
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNull(deleteBoard.getId());
    }
}

