package by.training.homework7.util;

import java.util.UUID;

public class IdGenerator {
    private IdGenerator() {
    }

    public static String createId() {
        UUID newId = UUID.randomUUID();
        return newId.toString();
    }
}
