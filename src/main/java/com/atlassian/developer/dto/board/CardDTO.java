package com.atlassian.developer.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("cover")
    private CardCoverDTO cover;

    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("pos")
    private String pos;

    @JsonProperty("idList")
    private String idList;

    public String getIdList() {
        return idList;
    }

    public String getId() {
        return id;
    }

    public String getCardName() {
        return name;
    }


    public CardCoverDTO getCover() {
        return cover;
    }

    public void setCover(CardCoverDTO cover) {
        this.cover = cover;
    }
}

