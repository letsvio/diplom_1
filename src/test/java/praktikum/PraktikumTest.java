package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PraktikumTest {



    @Test
    @DisplayName("Метод main выполняется без ошибок и печатает чек")
    void mainShouldBeCovered() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        assertDoesNotThrow(() -> Praktikum.main(new String[]{}));

        System.setOut(original);

        String output = out.toString();

        assertNotNull(output);
        assertFalse(output.isEmpty());

        assertTrue(output.contains("bun"));
        assertTrue(output.contains("Price"));
    }

}
