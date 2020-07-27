package controller.command;

import by.training.homework7.controller.command.impl.EmptyCommand;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class EmptyCommandTest {
    EmptyCommand command;

    @BeforeClass
    public void setUp() {
        command = new EmptyCommand();
    }

    @Test
    public void executeTestValid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("Empty command...", "Unsuccessful reply... ");
        Map<String, String> actual = command.execute("Some parameters");
        assertEquals(expected, actual);
    }

    @Test
    public void executeTestInvalid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("ERROR", "Unsuccessful");
        Map<String, String> actual = command.execute("Some parameters");
        assertNotEquals(expected, actual);
    }

    @AfterClass
    public void tierDown() {
        command = null;
    }
}
