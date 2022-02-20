package com.atlassian.developer.dto.board;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

@JsonRootName(value = "cover")
@XmlRootElement(name="cover")
public class CardCoverDTO {

    private String idAttachment;

    @JsonSerialize (using = Color.ColorSerializer.class)
    private Color color;


    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }
}

