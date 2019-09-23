package telran.security.entities;

import java.util.Arrays;

public enum Roles {
    USER("user"),
    SUPERUSER("superuser");


    String value;

    Roles(String value) {
        this.value = value;
    }

    public static String[] getValues() {
        return (String[]) Arrays.stream(values())
                .map(genreEntity -> genreEntity.value)
                .toArray();
    }
}
