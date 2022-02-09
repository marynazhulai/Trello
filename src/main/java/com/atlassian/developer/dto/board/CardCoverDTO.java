package com.atlassian.developer.dto.board;

public class CardCoverDTO {

    private String idAttachment;

    private String color;


    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        switch (color) {
            case "yellow":
            case "blue":
                this.color = color;
                break;
            default:
                System.out.println("Incorrect color");
        }
    }
}
