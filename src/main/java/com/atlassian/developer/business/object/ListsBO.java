package com.atlassian.developer.business.object;

import com.atlassian.developer.dto.board.BoardDTO;
import com.atlassian.developer.dto.board.ListService;
import com.atlassian.developer.dto.board.ListsDTO;
import org.apache.http.HttpStatus;

import java.util.List;

public class ListsBO {
    BoardDTO board;

    public ListsBO(BoardDTO board) {
        this.board = board;
    }

    public List<ListsDTO> getLists() {
        final List<ListsDTO> lists = new ListService().getMemberLists(board.getId())
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$", ListsDTO.class);
        return lists;
    }
}
