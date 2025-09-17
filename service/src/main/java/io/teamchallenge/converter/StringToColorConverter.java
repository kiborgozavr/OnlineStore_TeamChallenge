package io.teamchallenge.converter;

import io.teamchallenge.enumerated.Color;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StringToColorConverter implements Converter<String, Color> {

    @Override
    public Color convert(String source) {
        if (source == null) return null;
        try {
            return Color.valueOf(source.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(generateErrorMessage(source));
        }
    }

    private String generateErrorMessage(String color) {
        return "Color %s is not recognized".formatted(color) +
            "Allowed colors are: " + Arrays.toString(Color.values());
    }
}
