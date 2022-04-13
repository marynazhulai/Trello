package com.atlassian.developer;
import com.atlassian.developer.dto.board.BoardDTO;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BaseTest {
    static BoardDTO board;

    @BeforeMethod
    public void createBoard() {
        final BoardService boardService = new BoardService();
        final String name = Faker.instance().funnyName().name();
        board = boardService.createBoard(name)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(BoardDTO.class);
        assertNotNull(board.getId());
        assertEquals(board.getName(), name, "Name not match");
        final List<BoardDTO> boardList = new MemberService().getMemberBoards()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$", BoardDTO.class);
        assertTrue(boardList.stream().anyMatch(b -> b.getId().equals(board.getId())));
    }

    @AfterMethod
    public void deleteBoard() {
        final BoardService boardService = new BoardService();
        if (board.getId() != null) {
            final BoardDTO deleteBoard = boardService.deleteBoard(board.getId())
                    .assertThat()
                    .statusCode(org.apache.hc.core5.http.HttpStatus.SC_OK)
                    .extract()
                    .body()
                    .as(BoardDTO.class);
        }
    }
}
