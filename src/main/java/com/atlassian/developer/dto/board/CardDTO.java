package com.atlassian.developer.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDTO {

    @JsonProperty ("name")
    private String name;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty ("pos")
    private String pos;

}
