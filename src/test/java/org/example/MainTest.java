package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testSendGreetings() {
        assertEquals("Hello world!", Main.sendGreetings());
    }
}