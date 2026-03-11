package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    @DisplayName("Конструктор сохраняет имя и цену")
    void constructorShouldSetNameAndPrice() {
        Bun bun = new Bun("булочка для теста", 149.99f);

        assertEquals("булочка для теста", bun.getName());
        assertEquals(149.99f, bun.getPrice(), 0.001f);
    }

    @ParameterizedTest
    @CsvSource({
            "чёрная булочка , 100.0",
            "белая булочка,   0.0",
            "красная булочка,   300.5",
            "'очень большое название вкусной булочки',  -50.0"
    })
    @DisplayName("getName и getPrice возвращают корректные значения")
    void gettersShouldReturnConstructorValues(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}