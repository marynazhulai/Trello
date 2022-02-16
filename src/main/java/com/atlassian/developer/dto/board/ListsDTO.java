package com.atlassian.developer.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListsDTO {

    @JsonProperty("id")
    private String id;



    public String getIdList (List<ListsDTO> list) {
        return list.get(0).getId();
    }

    /*public ListsDTO getIdList (List<ListsDTO> list) {
        return list.get(0);
    }*/

    public String getId () {
        return id;
    }

}
