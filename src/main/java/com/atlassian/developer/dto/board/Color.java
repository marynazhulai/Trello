package com.atlassian.developer.dto.board;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public enum Color {
    YELLOW("yellow"),
    BLUE("blue");

    private String colorCode;

    Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public static class ColorSerializer extends JsonSerializer<Color> {

        @Override
        public void serialize(Color color, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (color.getColorCode() != null) {
                jsonGenerator.writeString(color.getColorCode());
            } else {
                jsonGenerator.writeNull();
            }
        }
    }
}

