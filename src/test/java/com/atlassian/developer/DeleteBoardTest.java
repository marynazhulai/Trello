package com.atlassian.developer;

import com.atlassian.developer.dto.board.BoardDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

public class DeleteBoardTest {
    public String boardId = "620d19a0ca8bef43cd36f624";

    @Test
    public void deleteBoard() {
        final BoardService boardService = new BoardService();

        final BoardDTO deleteBoard = boardService.deleteBoard(boardId)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
    }
}

