package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    @DisplayName("availableBuns возвращает список из 3 булок")
    void availableBunsShouldReturnThreeBuns() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    @Test
    @DisplayName("availableIngredients возвращает 6 ингредиентов")
    void availableIngredientsShouldReturnSixItems() {
        assertEquals(6, database.availableIngredients().size());
    }

    @Test
    @DisplayName("В списке булок есть ожидаемые названия")
    void bunsListContainsExpectedNames() {
        List<String> names = database.availableBuns().stream()
                .map(Bun::getName)
                .collect(Collectors.toList());

        assertTrue(names.contains("black bun"));
        assertTrue(names.contains("white bun"));
        assertTrue(names.contains("red bun"));
    }

    @Test
    @DisplayName("Список ингредиентов содержит хотя бы по одному SAUCE и FILLING")
    void ingredientsContainBothTypes() {
        long sauces = database.availableIngredients().stream()
                .filter(i -> i.getType() == IngredientType.SAUCE)
                .count();

        long fillings = database.availableIngredients().stream()
                .filter(i -> i.getType() == IngredientType.FILLING)
                .count();

        assertTrue(sauces >= 1);
        assertTrue(fillings >= 1);
    }
}