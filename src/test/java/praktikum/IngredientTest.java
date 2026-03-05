package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    @DisplayName("Конструктор корректно сохраняет все поля")
    void constructorShouldInitializeAllFields() {
        Ingredient ing = new Ingredient(IngredientType.SAUCE, "соевый", 45.5f);
        assertEquals(IngredientType.SAUCE, ing.getType());
        assertEquals("соевый", ing.getName());
        assertEquals(45.5f, ing.getPrice(), 0.001f);
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("getType возвращает переданный тип")
    void getTypeReturnsCorrectValue(IngredientType type) {
        Ingredient ing = new Ingredient(type, "тест", 10f);
        assertEquals(type, ing.getType());
    }

    @ParameterizedTest
    @CsvSource({
            "кетчуп,  99.9",
            "котлетка,   0.0",
            "мясо кабанчика, 250.75"
    })
    void getNameAndGetPriceShouldReturnCorrectValues(String name, float price) {
        Ingredient ing = new Ingredient(IngredientType.FILLING, name, price);
        assertEquals(name, ing.getName());
        assertEquals(price, ing.getPrice(), 0.001f);
    }
}